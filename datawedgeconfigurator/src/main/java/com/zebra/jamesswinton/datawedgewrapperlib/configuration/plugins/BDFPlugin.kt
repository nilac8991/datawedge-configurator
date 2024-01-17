package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.bdf.OutputPluginName

class BDFPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle().apply {
            putString(BDF_ENABLED_KEY, if (builder.enabled) "true" else "false")
            putString(BDF_PREFIX_KEY, builder.prefix)
            putString(BDF_SUFFIX_KEY, builder.suffix)
            putString(BDF_SEND_DATA_KEY, if (builder.sendData) "true" else "false")
            putString(BDF_SEND_HEX_KEY, if (builder.sendHex) "true" else "false")
            putString(BDF_SEND_TAB_KEY, if (builder.sendTab) "true" else "false")
            putString(BDF_SEND_ENTER_KEY, if (builder.sendEnter) "true" else "false")
        }

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
        plugin.putString("OUTPUT_PLUGIN_NAME", builder.outputPluginName.name)
    }

    class Builder {
        // Config
        internal var resetConfig = true
        internal var outputPluginName = OutputPluginName.INTENT

        // Params
        internal var enabled = false
        internal var prefix = ""
        internal var suffix = ""
        internal var sendData = false
        internal var sendHex = false
        internal var sendTab = false
        internal var sendEnter = false

        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setOutputPluginName(outputPluginName: OutputPluginName): Builder {
            this.outputPluginName = outputPluginName
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.enabled = enabled
            return this
        }

        fun setPrefix(prefix: String): Builder {
            this.prefix = prefix
            return this
        }

        fun setSuffix(suffix: String): Builder {
            this.suffix = suffix
            return this
        }

        fun sendData(sendData: Boolean): Builder {
            this.sendData = sendData
            return this
        }

        fun sendHex(sendHex: Boolean): Builder {
            this.sendHex = sendHex
            return this
        }

        fun sendTab(sendTab: Boolean): Builder {
            this.sendTab = sendTab
            return this
        }

        fun sendEnter(sendEnter: Boolean): Builder {
            this.sendEnter = sendEnter
            return this
        }

        fun create(): Bundle {
            return BDFPlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "BDF"

        private const val BDF_ENABLED_KEY = "bdf_enabled"
        private const val BDF_PREFIX_KEY = "bdf_prefix"
        private const val BDF_SUFFIX_KEY = "bdf_suffix"
        private const val BDF_SEND_DATA_KEY = "bdf_send_data"
        private const val BDF_SEND_HEX_KEY = "bdf_send_hex"
        private const val BDF_SEND_TAB_KEY = "bdf_send_tab"
        private const val BDF_SEND_ENTER_KEY = "bdf_send_enter"
    }
}