package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

class WorkflowIdentificationDecoderModule() : WorkflowGenericModule() {

    init {
        name = "IDDecoderModule"
    }

    constructor(sessionTimeOut: Long) : this() {
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(sessionTimeOut: Long, outputImage: WorkflowOutputImageMode) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }
}