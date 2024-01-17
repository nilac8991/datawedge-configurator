package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle

class EKBPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        plugin.putString(EKB_ENABLED_KEY, if (builder.enabled) "true" else "false")
        plugin.putBundle(EKB_LAYOUT_KEY, builder.layout)
    }

    class Builder {

        internal var enabled = false
        internal var layout: Bundle? = null

        fun setEnabled(enabled: Boolean): Builder {
            this.enabled = enabled
            return this
        }

        fun setLayout(layoutGroup: String, layoutName: String): Builder {
            val layoutBundle = Bundle()
            layoutBundle.putString("layout_group", layoutGroup)
            layoutBundle.putString("layout_name ", layoutName)
            layout = layoutBundle
            return this
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