package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow

enum class WorkflowOutputImageMode(val mode: String = "") {
    FULL("full"),
    NONE("none");

    companion object {
        fun forMode(mode: String) = values().find { it.mode == mode }
    }
}