package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowOutputImageMode

data class WorkflowMeterDecoderModule(
        var scanMode: ScanMode = ScanMode.AUTO_ANALOG_DIGITAL_METER
) : WorkflowGenericModule() {

    init {
        name = "MeterReaderModule"
    }

    constructor(scanMode: ScanMode, sessionTimeOut: Long) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
    }

    constructor(scanMode: ScanMode, sessionTimeOut: Long, outputImage: WorkflowOutputImageMode) : this() {
        this.scanMode = scanMode
        this.sessionTimeOut = sessionTimeOut
        this.outputImage = outputImage
    }

    enum class ScanMode(val mode: String = "") {
        DIAL_METER("DIAL_METER"),
        AUTO_ANALOG_DIGITAL_METER("AUTO_ANALOG_DIGITAL_METER");

        companion object {
            fun forMode(mode: String) = values().find { it.mode == mode }
        }
    }
}