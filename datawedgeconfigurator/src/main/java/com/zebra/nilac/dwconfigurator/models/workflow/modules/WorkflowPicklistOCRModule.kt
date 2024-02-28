package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCRLanguageScript
import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCROutputImageMode
import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowPicklistOCRGenericRule

class WorkflowPicklistOCRModule() : WorkflowOCRGenericModule() {

    internal var confidenceLevel: Int = 75
    internal var rules: ArrayList<WorkflowPicklistOCRGenericRule> = arrayListOf()

    init {
        name = "MlKitExModule"
        sessionTimeOut = 1500
        outputImage = WorkflowOCROutputImageMode.CROPPED
    }

    constructor(
        sessionTimeOut: Long,
        confidenceLevel: Int,
        illumination: WorkflowCameraModule.Illumination,
        outputImage: WorkflowOCROutputImageMode,
        script: WorkflowOCRLanguageScript = WorkflowOCRLanguageScript.LATIN,
        rules: ArrayList<WorkflowPicklistOCRGenericRule> = arrayListOf()
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.confidenceLevel = confidenceLevel
        this.illumination = illumination
        this.outputImage = outputImage
        this.script = script
        this.rules = rules
    }

    constructor(
        sessionTimeOut: Long,
        script: WorkflowOCRLanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.script = script
        this.rules = rules
    }

    constructor(
        outputImage: WorkflowOCROutputImageMode,
        script: WorkflowOCRLanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.outputImage = outputImage
        this.script = script
        this.rules = rules
    }

    constructor(
        script: WorkflowOCRLanguageScript,
        rules: ArrayList<WorkflowPicklistOCRGenericRule>
    ) : this() {
        this.script = script
        this.rules = rules
    }

    constructor(rules: ArrayList<WorkflowPicklistOCRGenericRule>) : this() {
        this.rules = rules
    }
}