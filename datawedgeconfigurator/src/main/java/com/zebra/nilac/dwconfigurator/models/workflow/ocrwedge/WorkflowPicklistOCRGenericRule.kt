package com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge

import android.os.Bundle

data class WorkflowPicklistOCRGenericRule(
    var name: String,
    var identifierList: ArrayList<WorkflowPickListOCRIdentifier>
) {

    private var actionList: ArrayList<WorkflowPicklistOCRRuleAction> = arrayListOf(
        WorkflowPicklistOCRRuleAction(WorkflowPicklistOCRRuleAction.Action.REPORT, "")
    )

    constructor(name: String) : this(name, ArrayList())

    fun getIdentifiersListBundle(): ArrayList<Bundle> {
        val bundleList: ArrayList<Bundle> = ArrayList()
        for (identifier in identifierList) {
            bundleList.add(identifier.transformToBundle())
        }
        return bundleList
    }

    internal fun getActionListBundle(): ArrayList<Bundle> {
        val bundleList: ArrayList<Bundle> = ArrayList()
        for (action in actionList) {
            bundleList.add(action.transformToBundle())
        }
        return bundleList
    }
}