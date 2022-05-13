package com.zebra.jamesswinton.datawedgewrapperlib.models.barcode

import android.os.Bundle

data class BarcodeHighlightOverlayRule(
    var name: String,
    var criteriaList: ArrayList<BarcodeHighlightRuleCriteria>,
    var actionList: ArrayList<BarcodeHighlightRuleAction>
) {

    constructor(name: String) : this(name, ArrayList(), ArrayList())

    fun getCriteriaListBundle(): ArrayList<Bundle> {
        val bundleList: ArrayList<Bundle> = ArrayList()
        for (criteria in criteriaList) {
            bundleList.add(criteria.transformToBundle())
        }
        return bundleList
    }

    fun getActionListBundle(): ArrayList<Bundle> {
        val bundleList: ArrayList<Bundle> = ArrayList()
        for (action in actionList) {
            bundleList.add(action.transformToBundle())
        }
        return bundleList
    }
}