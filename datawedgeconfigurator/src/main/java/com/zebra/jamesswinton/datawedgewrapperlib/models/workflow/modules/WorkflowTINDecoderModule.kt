package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

class WorkflowTINDecoderModule(
    var scanMode: ScanMode = ScanMode.UNIVERSAL
) : WorkflowGenericModule() {

    init {
        name = "TinDecoderModule"
    }

    constructor(scanMode: ScanMode, sessionTimeOut: Long) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(
        scanMode: ScanMode,
        sessionTimeOut: Long,
        outputImage: WorkflowOutputImageMode
    ) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    enum class ScanMode(val mode: String = "") {
        US_DOT_1("DOT_STRICT"),
        US_DOT_2("DOT"),
        UNIVERSAL("UNIVERSAL"),
        COMMERCIAL_TIRE("COMMERCIAL_TIRE"),
        TIRE_SIZE("TIRE_SIZE");

        companion object {
            fun forMode(mode: String) = values().find { it.mode == mode }
        }
    }
}