package com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.highlight

enum class BarcodeHighlightSymbology(val symbology: String = "") {
    UPC_A("decoder_upca"),
    QRCODE("decoder_qrcode");

    companion object {
        fun forSymbology(symbology: String) = values().find { it.symbology == symbology }
    }
}