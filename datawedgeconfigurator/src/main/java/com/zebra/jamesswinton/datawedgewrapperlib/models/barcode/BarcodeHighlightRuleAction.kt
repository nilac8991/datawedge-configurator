package com.zebra.jamesswinton.datawedgewrapperlib.models.barcode

import android.os.Bundle

data class BarcodeHighlightRuleAction(
    var actionKey: Action,
    var actionValue: String
) {

    constructor(actionKey: Action) : this(actionKey, "")

    enum class Action(val action: String = "") {
        FILL_COLOR("fillcolor"),
        REPORT("report");

        companion object {
            fun forAction(action: String) = values().find { it.action == action }
        }
    }

    fun transformToBundle(): Bundle {
        return Bundle().apply {
            putString("action_key", actionKey.action);
            if (actionValue.isNotBlank()) {
                putString("action_value", actionValue);
            }
        }
    }
}

