package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow

enum class WorkflowMode(val mode: String = "") {
    LICENSE_PLATE("license_plate"),
    ID("id_scanning"),
    VIN("vin_number"),
    TIN("tin_number"),
    METER("meter_reading"),
    FREE_FORM_CAPTURE("free_form_capture");

    companion object {
        fun forMode(mode: String) = values().find { it.mode == mode }
    }
}