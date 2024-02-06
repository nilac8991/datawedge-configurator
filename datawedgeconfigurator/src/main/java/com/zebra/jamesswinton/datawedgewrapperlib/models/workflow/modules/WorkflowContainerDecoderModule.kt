package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

data class WorkflowContainerDecoderModule(
    var orientation: Orientation = Orientation.VERTICAL
) : WorkflowGenericModule() {

    init {
        name = "ContainerDecoderModule"
    }

    constructor(orientation: Orientation, sessionTimeOut: Long) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.orientation = orientation
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
        this.orientation = orientation
    }

    enum class Orientation(val type: String = "") {
        HORIZONTAL("Horizontal"),
        VERTICAL("Vertical");

        companion object {
            fun forType(type: String) = values().find { it.type == type }
        }
    }
}