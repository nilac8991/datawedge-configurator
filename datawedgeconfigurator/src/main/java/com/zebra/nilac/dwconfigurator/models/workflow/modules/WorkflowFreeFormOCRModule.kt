package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCRLanguageScript
import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCROutputImageMode

class WorkflowFreeFormOCRModule() : WorkflowOCRGenericModule() {

    init {
        this.name = "MLKitModule"
        this.outputImage = WorkflowOCROutputImageMode.CROPPED
    }

    constructor(
        sessionTimeOut: Long,
        illumination: WorkflowCameraModule.Illumination,
        outputImage: WorkflowOCROutputImageMode,
        script: WorkflowOCRLanguageScript = WorkflowOCRLanguageScript.LATIN
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.illumination = illumination
        this.outputImage = outputImage
        this.script = script
    }

    constructor(
        sessionTimeOut: Long,
        illumination: WorkflowCameraModule.Illumination,
        script: WorkflowOCRLanguageScript = WorkflowOCRLanguageScript.LATIN
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.illumination = illumination
        this.script = script
    }

    constructor(
        sessionTimeOut: Long,
        script: WorkflowOCRLanguageScript
    ) : this() {
        this.sessionTimeOut = sessionTimeOut
        this.script = script
    }

    constructor(
        outputImage: WorkflowOCROutputImageMode,
        script: WorkflowOCRLanguageScript,
    ) : this() {
        this.outputImage = outputImage
        this.script = script
    }
}