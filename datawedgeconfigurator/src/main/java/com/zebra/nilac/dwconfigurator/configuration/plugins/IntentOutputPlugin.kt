package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.intent.IntentOutputComponentInfo
import com.zebra.nilac.dwconfigurator.models.intent.IntentOutputDelivery
import java.util.Locale

class IntentOutputPlugin private constructor(builder: Builder) : GenericPlugin() {

    init {
        this.pluginName = PLUGIN_NAME

        paramList.apply {
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

        plugin.putString(PLUGIN_NAME_KEY, pluginName)
        plugin.putString(RESET_CONFIG_KEY, if (builder.resetConfig) "true" else "false")
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

        fun resetConfig(resetConfig: Boolean): Builder =
            apply { this.resetConfig = resetConfig }

        fun setEnabled(state: Boolean): Builder =
            apply { this.intentOutputEnabled = state }

        fun setIntentAction(action: String): Builder =
            apply { this.intentAction = action }

        fun setIntentCategory(category: String): Builder =
            apply { this.intentCategory = category }

        fun setIntentOutputDelivery(outputDelivery: IntentOutputDelivery): Builder =
            apply { this.intentOutputDelivery = outputDelivery }

        fun setIntentOutputReceiverForegroundFlagEnabled(state: Boolean): Builder {
            this.intentOutputReceiverForegroundFlag = state
            return this
        }

        fun setUseContentProviderEnabled(state: Boolean): Builder =
            apply { this.intentOutputUseContentProvider = state }

        fun addNewIntentComponentInfoObject(componentInfoObject: IntentOutputComponentInfo): Builder =
            apply { intentComponentsInfo.add(componentInfoObject) }

        fun create(): Bundle {
            return IntentOutputPlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "INTENT"

        private const val INTENT_OUTPUT_ENABLE = "intent_output_enabled"
        private const val INTENT_OUTPUT_ACTION = "intent_action"
        private const val INTENT_OUTPUT_CATEGORY = "intent_category"
        private const val INTENT_OUTPUT_DELIVERY = "intent_delivery"
        private const val INTENT_OUTPUT_COMPONENT_INFO = "intent_component_info"
        private const val INTENT_OUTPUT_RECEIVER_FOREGROUND_FLAG = "intent_flag_receiver_foreground"
        private const val INTENT_OUTPUT_USE_CONTENT_PROVIDER = "intent_use_content_provider"
    }
}