package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowOutputImageMode

open class WorkflowGenericModule(
        var name: String = "",
        var sessionTimeOut: Long = 20000,
        var outputImage: WorkflowOutputImageMode = WorkflowOutputImageMode.FULL
) {
}