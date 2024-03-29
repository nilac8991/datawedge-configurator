package com.zebra.nilac.dwconfigurator.models.barcode.highlight

import android.os.Bundle

data class BarcodeHighlightRuleCriteria(
    var criteriaKey: Criteria,
    var criteriaValue: String
) {

    enum class Criteria(val criteria: String = "") {
        MIN_LENGTH("min_length"),
        MAX_LENGTH("max_length"),
        CONTAINS("contains"),
        IGNORE_CASE("ignore_case");

        companion object {
            fun forCriteria(criteria: String) = values().find { it.criteria == criteria }
        }
    }

    fun transformToBundle(): Bundle {
        return Bundle().apply {
            putString("criteria_key", criteriaKey.criteria);
            putString("criteria_value", criteriaValue);
        }
    }
}

