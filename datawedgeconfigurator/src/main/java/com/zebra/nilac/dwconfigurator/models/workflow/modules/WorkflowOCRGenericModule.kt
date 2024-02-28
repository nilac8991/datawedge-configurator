package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCRLanguageScript
import com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge.WorkflowOCROutputImageMode

open class WorkflowOCRGenericModule(
    internal var name: String = "",
    internal var sessionTimeOut: Long = 20000,
    internal var outputImage: WorkflowOCROutputImageMode = WorkflowOCROutputImageMode.DISABLED,
    internal var illumination: WorkflowCameraModule.Illumination = WorkflowCameraModule.Illumination.OFF,
    internal var script: WorkflowOCRLanguageScript = WorkflowOCRLanguageScript.LATIN,
) {
}