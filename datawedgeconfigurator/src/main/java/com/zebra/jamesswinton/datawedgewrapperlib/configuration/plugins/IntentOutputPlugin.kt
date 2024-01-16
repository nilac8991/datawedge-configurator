package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.intent.IntentOutputComponentInfo
import com.zebra.jamesswinton.datawedgewrapperlib.models.intent.IntentOutputDelivery
import java.util.Locale

class IntentOutputPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        val paramList = Bundle().apply {
            putString(INTENT_OUTPUT_ENABLE, if (builder.intentOutputEnabled) "true" else "false")
            putString(INTENT_OUTPUT_ACTION, builder.intentAction)
            putString(INTENT_OUTPUT_CATEGORY, builder.intentCategory)
            putString(
                INTENT_OUTPUT_DELIVERY,
                String.format(Locale.getDefault(), "%d", builder.intentOutputDelivery.ordinal)
            )
            putString(
                INTENT_OUTPUT_USE_CONTENT_PROVIDER,
                if (builder.intentOutputUseContentProvider) "true" else "false"
            )
            putString(
                INTENT_OUTPUT_RECEIVER_FOREGROUND_FLAG,
                if (builder.intentOutputReceiverForegroundFlag) "true" else "false"
            )

            val bundleList: ArrayList<Bundle> = ArrayList()
            for (intentComponentInfo in builder.intentComponentsInfo) {
                bundleList.add(intentComponentInfo.transformToBundle())
            }
            putParcelableArrayList(INTENT_OUTPUT_COMPONENT_INFO, bundleList)
        }

        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
    }

    class Builder {

        // Config
        var resetConfig = true

        //Params
        internal var intentOutputEnabled = false
        internal var intentAction = ""
        internal var intentCategory = "android.intent.category.DEFAULT"
        internal var intentOutputDelivery = IntentOutputDelivery.BROADCAST
        internal var intentOutputReceiverForegroundFlag = false
        internal var intentOutputUseContentProvider = false

        internal val intentComponentsInfo = ArrayList<IntentOutputComponentInfo>()

        fun setEnabled(state: Boolean): Builder {
            this.intentOutputEnabled = state
            return this
        }

        fun setIntentAction(action: String): Builder {
            this.intentAction = action
            return this
        }

        fun setIntentCategory(category: String): Builder {
            this.intentCategory = category
            return this
        }

        fun setIntentOutputDelivery(outputDelivery: IntentOutputDelivery): Builder {
            this.intentOutputDelivery = outputDelivery
            return this
        }

        fun setIntentOutputReceiverForegroundFlagEnabled(state: Boolean): Builder {
            this.intentOutputReceiverForegroundFlag = state
            return this
        }

        fun setUseContentProviderEnabled(state: Boolean): Builder {
            this.intentOutputUseContentProvider = state
            return this
        }

        fun addNewIntentComponentInfoObject(componentInfoObject: IntentOutputComponentInfo): Builder {
            intentComponentsInfo.add(componentInfoObject)
            return this
        }

        fun create(): Bundle {
            return IntentOutputPlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "INTENT"

        const val INTENT_OUTPUT_ENABLE = "intent_output_enabled"
        const val INTENT_OUTPUT_ACTION = "intent_action"
        const val INTENT_OUTPUT_CATEGORY = "intent_category"
        const val INTENT_OUTPUT_DELIVERY = "intent_delivery"
        const val INTENT_OUTPUT_COMPONENT_INFO = "intent_component_info"
        const val INTENT_OUTPUT_RECEIVER_FOREGROUND_FLAG = "intent_flag_receiver_foreground"
        const val INTENT_OUTPUT_USE_CONTENT_PROVIDER = "intent_use_content_provider"
    }
}