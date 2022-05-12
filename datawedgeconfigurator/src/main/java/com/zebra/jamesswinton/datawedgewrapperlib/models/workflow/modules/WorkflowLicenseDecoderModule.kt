package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

data class WorkflowLicenseDecoderModule(
        var scanMode: ScanMode = ScanMode.US
) : WorkflowGenericModule() {

    init {
        name = "LicenseDecoderModule"
    }

    constructor(scanMode: ScanMode, sessionTimeOut: Long) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(scanMode: ScanMode, sessionTimeOut: Long, outputImage: WorkflowOutputImageMode) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    enum class ScanMode(val mode: String = "") {
        US("unitedstates"),
        EU("auto");

        companion object {
            fun forMode(mode: String) = values().find { it.mode == mode }
        }
    }
}