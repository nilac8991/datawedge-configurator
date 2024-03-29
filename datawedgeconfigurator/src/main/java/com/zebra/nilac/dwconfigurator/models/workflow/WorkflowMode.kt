package com.zebra.nilac.dwconfigurator.models.workflow

enum class WorkflowMode(val mode: String = "") {
    FREE_FORM_OCR("free_form_ocr"),
    PICKLIST_OCR("picklist_ocr"),
    LICENSE_PLATE("license_plate"),
    ID("id_scanning"),
    VIN("vin_number"),
    TIN("tin_number"),
    METER("meter_reading"),
    CONTAINER("container_scanning"),
    FREE_FORM_CAPTURE("free_form_capture");

    companion object {
        fun forMode(mode: String) = values().find { it.mode == mode }
    }
}