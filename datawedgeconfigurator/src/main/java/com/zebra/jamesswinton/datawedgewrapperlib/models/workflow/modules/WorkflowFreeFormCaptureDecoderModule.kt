package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

data class WorkflowFreeFormCaptureDecoderModule(
    var mode: Mode = Mode.HIGHLIGHT
) : WorkflowGenericModule() {

    init {
        name = "BarcodeTrackerModule"
    }

    constructor(mode: Mode, sessionTimeOut: Long) : this() {
        this.mode = mode
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(
        scanMode: Mode,
        sessionTimeOut: Long,
        outputImage: WorkflowOutputImageMode
    ) : this() {
        this.mode = scanMode
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    enum class Mode {
        OFF, HIGHLIGHT, DECODE_AND_HIGHLIGHT
    }
}