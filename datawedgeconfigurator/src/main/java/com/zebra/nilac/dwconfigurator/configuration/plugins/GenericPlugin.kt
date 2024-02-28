package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle

open class GenericPlugin {

    internal var plugin = Bundle()
    internal var paramList = Bundle()

    internal var pluginName = ""

    init {
        plugin.putBundle(PARAM_LIST_KEY, paramList)
    }

    companion object {
        const val PLUGIN_NAME_KEY = "PLUGIN_NAME"
        const val RESET_CONFIG_KEY = "RESET_CONFIG"
        const val PARAM_LIST_KEY = "PARAM_LIST"
    }
}