package com.nlmk.barcodescanning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun scanBarcodes(image: InputImage) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE
            )
            .build()
        val scanner = BarcodeScanning.getClient()
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    val bounds = barcode.boundingBox
                    val corners = barcode.cornerPoints
                    val rawValue = barcode.rawValue
                    val valueType = barcode.valueType

                    when (valueType) {
                        Barcode.TYPE_WIFI -> {
                            val ssid = barcode?.wifi?.ssid
                            val password = barcode?.wifi?.password
                            val type = barcode?.wifi?.encryptionType
                        }
                        Barcode.TYPE_URL -> {
                            val title = barcode?.url?.title
                            val url = barcode?.url?.title
                        }
                    }
                }
            }
            .addOnFailureListener { }
    }
}