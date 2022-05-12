package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

class WorkflowTINDecoderModule() : WorkflowGenericModule() {

    init {
        name = "TinDecoderModule"
    }

    constructor(sessionTimeOut: Long) : this() {
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(sessionTimeOut: Long, outputImage: WorkflowOutputImageMode) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }
}