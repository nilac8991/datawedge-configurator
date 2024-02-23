package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowPicklistOCRGenericRule

class WorkflowPicklistOCRModule(
    var sessionTimeOut: Int = 1500,
    var confidenceLevel: Int = 75,
    var illumination: WorkflowCameraModule.Illumination = WorkflowCameraModule.Illumination.OFF,
    var outputImage: OutputImage = OutputImage.CROPPED,
    var script: LanguageScript = LanguageScript.LATIN,
    var rules: ArrayList<WorkflowPicklistOCRGenericRule> = arrayListOf()
) {

    internal val name = "MlKitExModule"

    constructor(
        sessionTimeOut: Int,
        script: LanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.script = script
        this.rules = rules
    }

    constructor(
        outputImage: OutputImage,
        script: LanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.outputImage = outputImage
        this.script = script
        this.rules = rules
    }

    constructor(
        script: LanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.script = script
        this.rules = rules
    }

    constructor(rules: ArrayList<WorkflowPicklistOCRGenericRule>) : this() {
        this.rules = rules
    }

    enum class OutputImage(val modeValue: Int = 0) {
        DISABLED(0), CROPPED(2)
    }

    enum class LanguageScript() {
        LATIN, LATIN_CHINESE, LATIN_KOREAN, LATIN_DEVANAGARI
    }
}