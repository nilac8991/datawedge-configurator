package com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.reader

enum class ReaderCharsetName(val charset: String = "") {
    AUTO("AUTO"),
    UTF_8("UTF-8"),
    ISO_8859_1("ISO-8859-1"),
    SHIFT_JIS("Shift_JIS"),
    GB18030("GB18030")
}