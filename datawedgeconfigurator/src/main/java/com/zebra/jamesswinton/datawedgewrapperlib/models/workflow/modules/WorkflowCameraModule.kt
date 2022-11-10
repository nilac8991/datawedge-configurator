package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

data class WorkflowCameraModule(
    var illumination: Illumination = Illumination.ON,
    var zoom: Int = 1
) {

    constructor(illumination: Illumination) : this() {
        this.illumination = illumination
    }

    constructor(zoom: Int) : this() {
        this.zoom = zoom
    }

    enum class Illumination(val mode: String = "") {
        ON("on"),
        OFF("off");

        companion object {
            fun forMode(mode: String) = values().find { it.mode == mode }
        }
    }
}