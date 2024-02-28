package com.zebra.nilac.dwconfigurator.models.workflow.modules

import com.zebra.nilac.dwconfigurator.models.workflow.WorkflowOutputImageMode

open class WorkflowGenericModule(
    var name: String = "",
    var sessionTimeOut: Long = 20000,
    var outputImage: WorkflowOutputImageMode = WorkflowOutputImageMode.FULL
) {
}