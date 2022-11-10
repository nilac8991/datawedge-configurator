package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

data class WorkflowContainerDecoderModule(
    var orientation: Orientation = Orientation.HORIZONTAL
) : WorkflowGenericModule() {

    init {
        name = "ContainerDecoderModule"
    }

    constructor(orientation: Orientation, sessionTimeOut: Long) : this() {
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(sessionTimeOut: Long, outputImage: WorkflowOutputImageMode) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    constructor(
        orientation: Orientation,
        sessionTimeOut: Long,
        outputImage: WorkflowOutputImageMode
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    constructor(sessionTimeOut: Long) : this() {
        this.sessionTimeOut = sessionTimeOut
    }

    enum class Orientation(val type: String = "") {
        HORIZONTAL("horizontal"),
        VERTICAL("vertical");

        companion object {
            fun forType(type: String) = values().find { it.type == type }
        }
    }
}