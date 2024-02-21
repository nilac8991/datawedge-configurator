package com.zebra.nilac.dwconfigurator.models.barcode.highlight

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeSymbology

data class BarcodeHighlightGenericRule(
    var name: String,
    var criteriaList: ArrayList<BarcodeHighlightRuleCriteria>,
    var actionList: ArrayList<BarcodeHighlightRuleAction>,
    var symbologies: ArrayList<BarcodeSymbology>
) {

    constructor(name: String) : this(name, ArrayList(), ArrayList(), ArrayList())

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

    fun getSymbologiesArray(): Array<String?> {
        val stringArray: Array<String?> = arrayOfNulls<String>(symbologies.size)
        if (symbologies.isNotEmpty()) {
            for (i in 0 until symbologies.size) {
                stringArray[i] = symbologies[i].symbology
            }
        }
        return stringArray
    }
}