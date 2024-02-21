package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowOutputImageMode

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