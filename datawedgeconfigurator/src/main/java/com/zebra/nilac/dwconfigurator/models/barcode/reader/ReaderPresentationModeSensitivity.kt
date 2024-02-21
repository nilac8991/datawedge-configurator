package com.zebra.nilac.dwconfigurator.models.barcode.reader

enum class ReaderPresentationModeSensitivity(val mode: Int = 0) {
    LOW(80),
    MEDIUM(120),
    HIGH(160)
}