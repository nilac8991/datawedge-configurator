package com.zebra.nilac.dwconfigurator.models.workflow.ocrwedge

import android.os.Bundle

internal data class WorkflowPicklistOCRRuleAction(
    var actionKey: Action,
    var actionValue: String
) {

    constructor(actionKey: Action) : this(actionKey, "")

    enum class Action(val action: String = "") {
        REPORT("report");
    }

    fun transformToBundle(): Bundle {
        return Bundle().apply {
            putString("action_key", actionKey.action);
            putString("action_value", actionValue);
        }
    }
}

