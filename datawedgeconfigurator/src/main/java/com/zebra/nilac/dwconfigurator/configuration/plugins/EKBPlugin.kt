package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle

class EKBPlugin private constructor(builder: Builder) : GenericPlugin() {

    init {
        plugin.apply {
            putString(EKB_ENABLED_KEY, if (builder.enabled) "true" else "false")
            putBundle(EKB_LAYOUT_KEY, builder.layout)
        }
    }

    class Builder {
        internal var enabled = false
        internal var layout: Bundle? = null

        fun setEnabled(enabled: Boolean): Builder =
            apply { this.enabled = enabled }

        fun setLayout(layoutGroup: String, layoutName: String): Builder =
            apply {
                this.layout = Bundle().apply {
                    putString("layout_group", layoutGroup)
                    putString("layout_name ", layoutName)
                }
            }

        fun create(): Bundle {
            return EKBPlugin(this).plugin
        }
    }

    companion object {
        private const val EKB_ENABLED_KEY = "ekb_enabled"
        private const val EKB_LAYOUT_KEY = "ekb_layout"
    }
}