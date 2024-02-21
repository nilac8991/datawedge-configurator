package com.zebra.nilac.dwconfigurator.models.intent

import android.os.Bundle

data class IntentOutputComponentInfo(
    var packageName: String,
    var signature: String
){
    fun transformToBundle(): Bundle {
        return Bundle().apply {
            putString("PACKAGE_NAME", packageName);
            putString("SIGNATURE", signature);
        }
    }
}