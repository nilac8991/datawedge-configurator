package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

data class WorkflowCameraModule(
        var illumination: Illumination = Illumination.ON
) {

    enum class Illumination(val mode: String = "") {
        ON("on"),
        OFF("off");

        companion object {
            fun forMode(mode: String) = values().find { it.mode == mode }
        }
    }
}