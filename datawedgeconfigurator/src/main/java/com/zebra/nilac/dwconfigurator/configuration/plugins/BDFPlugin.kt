package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.bdf.OutputPluginName

class BDFPlugin private constructor(builder: Builder) : GenericPlugin() {

    init {
        this.pluginName = PLUGIN_NAME

        // Build Params
        paramList.apply {
            putString(BDF_ENABLED_KEY, if (builder.enabled) "true" else "false")
            putString(BDF_PREFIX_KEY, builder.prefix)
            putString(BDF_SUFFIX_KEY, builder.suffix)
            putString(BDF_SEND_DATA_KEY, if (builder.sendData) "true" else "false")
            putString(BDF_SEND_HEX_KEY, if (builder.sendHex) "true" else "false")
            putString(BDF_SEND_TAB_KEY, if (builder.sendTab) "true" else "false")
            putString(BDF_SEND_ENTER_KEY, if (builder.sendEnter) "true" else "false")
        }

        // Build Plugin
        plugin.putString(PLUGIN_NAME_KEY, pluginName)
        plugin.putString(RESET_CONFIG_KEY, if (builder.resetConfig) "true" else "false")
        plugin.putString(OUTPUT_PLUGIN_NAME_KEY, builder.outputPluginName.name)
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

        fun resetConfig(resetConfig: Boolean): Builder =
            apply { this.resetConfig = resetConfig }

        fun setOutputPluginName(outputPluginName: OutputPluginName): Builder =
            apply { this.outputPluginName = outputPluginName }

        fun setEnabled(enabled: Boolean): Builder =
            apply { this.enabled = enabled }

        fun setPrefix(prefix: String): Builder =
            apply { this.prefix = prefix }

        fun setSuffix(suffix: String): Builder =
            apply { this.suffix = suffix }

        fun sendData(sendData: Boolean): Builder =
            apply { this.sendData = sendData }

        fun sendHex(sendHex: Boolean): Builder =
            apply { this.sendHex = sendHex }

        fun sendTab(sendTab: Boolean): Builder =
            apply { this.sendTab = sendTab }

        fun sendEnter(sendEnter: Boolean): Builder =
            apply { this.sendEnter = sendEnter }

        fun create(): Bundle {
            return BDFPlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "BDF"

        private const val OUTPUT_PLUGIN_NAME_KEY = "OUTPUT_PLUGIN_NAME"

        private const val BDF_ENABLED_KEY = "bdf_enabled"
        private const val BDF_PREFIX_KEY = "bdf_prefix"
        private const val BDF_SUFFIX_KEY = "bdf_suffix"
        private const val BDF_SEND_DATA_KEY = "bdf_send_data"
        private const val BDF_SEND_HEX_KEY = "bdf_send_hex"
        private const val BDF_SEND_TAB_KEY = "bdf_send_tab"
        private const val BDF_SEND_ENTER_KEY = "bdf_send_enter"
    }
}