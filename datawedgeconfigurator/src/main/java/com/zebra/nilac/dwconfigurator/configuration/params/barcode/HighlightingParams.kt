package com.zebra.nilac.dwconfigurator.configuration.params.barcode

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.barcode.highlight.BarcodeHighlightGenericRule

class HighlightingParams private constructor(builder: Builder) {

    private val params = Bundle()

    init {
        //Barcode Highlight
        params.putString(
            HIGHLIGHT_ENABLE_KEY,
            if (builder.enableBarcodeHighlight) "true" else "false"
        )

        val barcodeOverlayRuleList: ArrayList<Bundle> = ArrayList()
        for (overlayRule in builder.overlayRules) {
            val ruleBundle = Bundle().apply {
                putString(HIGHLIGHT_RULE_NAME_KEY, overlayRule.name)
                putBundle(HIGHLIGHT_CRITERIA_KEY, Bundle().apply {
                    putParcelableArrayList(
                        HIGHLIGHT_IDENTIFIER_KEY,
                        overlayRule.getCriteriaListBundle()
                    )
                    putStringArray(HIGHLIGHT_SYMBOLOGY_KEY, overlayRule.getSymbologiesArray())
                })
                putParcelableArrayList(HIGHLIGHT_ACTIONS_KEY, overlayRule.getActionListBundle())
            }
            barcodeOverlayRuleList.add(ruleBundle)
        }

        //Highlight Report Part
        val reportDataRuleList: ArrayList<Bundle> = ArrayList()
        for (reportDataRule in builder.reportDataRules) {
            val ruleBundle = Bundle().apply {
                putString(HIGHLIGHT_RULE_NAME_KEY, reportDataRule.name)
                putBundle(HIGHLIGHT_CRITERIA_KEY, Bundle().apply {
                    putParcelableArrayList(
                        HIGHLIGHT_IDENTIFIER_KEY,
                        reportDataRule.getCriteriaListBundle()
                    )
                    putStringArray(
                        HIGHLIGHT_SYMBOLOGY_KEY,
                        reportDataRule.getSymbologiesArray()
                    )
                })
                putParcelableArrayList(
                    HIGHLIGHT_ACTIONS_KEY,
                    reportDataRule.getActionListBundle()
                )
            }
            reportDataRuleList.add(ruleBundle)
        }

        val highlightingRules: ArrayList<Bundle> = ArrayList<Bundle>().apply {
            add(Bundle().apply {
                putString(HIGHLIGHT_RULE_PARAM_KEY, "barcode_overlay")
                putParcelableArrayList(HIGHLIGHT_RULE_LIST_KEY, barcodeOverlayRuleList)
            })
            add(Bundle().apply {
                putString(HIGHLIGHT_RULE_PARAM_KEY, "report_data")
                putParcelableArrayList(HIGHLIGHT_RULE_LIST_KEY, reportDataRuleList)
            })
        }
        params.putParcelableArrayList(HIGHLIGHT_RULES_KEY, highlightingRules)
    }

    class Builder {
        //Highlight
        internal var enableBarcodeHighlight = false
        internal val overlayRules = ArrayList<BarcodeHighlightGenericRule>()
        internal val reportDataRules = ArrayList<BarcodeHighlightGenericRule>()

        fun setEnabled(state: Boolean): Builder =
            apply { this.enableBarcodeHighlight = state }

        fun addNewBarcodeHighlightOverlayRule(rule: BarcodeHighlightGenericRule): Builder =
            apply { overlayRules.add(rule) }

        fun addNewBarcodeHighlightReportDataRule(rule: BarcodeHighlightGenericRule): Builder =
            apply { reportDataRules.add(rule) }

        fun create(): Bundle {
            return HighlightingParams(this).params
        }
    }

    companion object {
        internal const val HIGHLIGHT_ENABLE_KEY = "barcode_highlighting_enabled"
        internal const val HIGHLIGHT_RULES_KEY = "barcode_highlighting_rules"

        private const val HIGHLIGHT_RULE_NAME_KEY = "rule_name"
        private const val HIGHLIGHT_CRITERIA_KEY = "criteria"
        private const val HIGHLIGHT_IDENTIFIER_KEY = "identifier"
        private const val HIGHLIGHT_ACTIONS_KEY = "actions"
        private const val HIGHLIGHT_SYMBOLOGY_KEY = "symbology"
        private const val HIGHLIGHT_RULE_PARAM_KEY = "rule_param_id"
        private const val HIGHLIGHT_RULE_LIST_KEY = "rule_list"
    }
}