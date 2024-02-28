package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.keystroke.KeystrokeActionChar

class KeystrokeOutputPlugin private constructor(builder: Builder) : GenericPlugin() {

    init {
        this.pluginName = PLUGIN_NAME

        // Build Params
        paramList.apply {
            putString(KEYSTROKE_OUTPUT_ENABLED_KEY, if (builder.enabled) "true" else "false")
            putString(KEYSTROKE_ACTION_CHAR_KEY, builder.actionChar.name)
            putString(KEYSTROKE_DELAY_CONTROL_KEY, builder.delayControlChar.toString())
            putString(KEYSTROKE_CHARACTER_DELAY_KEY, builder.characterDelay.toString())
            putString(
                KEYSTROKE_DELAY_MULTIBYTE_CHARS_ONLY_KEY,
                if (builder.delayMultiByteCharsOnly) "true" else "false"
            )
            putString(
                KEYSTROKE_SEND_CHARS_AS_EVENTS_KEY,
                if (builder.sendCharsAsEvents) "true" else "false"
            )
            putString(
                KEYSTROKE_SEND_CONTROL_CHARS_AS_EVENTS_KEY,
                if (builder.sendControlCharsAsEvents) "true" else "false"
            )
            putString(
                KEYSTROKE_SEND_TAB_AS_STRING_KEY,
                if (builder.sendTabAsString) "true" else "false"
            )
        }

        plugin.putString(PLUGIN_NAME_KEY, pluginName)
        plugin.putString(RESET_CONFIG_KEY, if (builder.resetConfig) "true" else "false")
    }

    class Builder {
        // Config
        internal var resetConfig = true

        // Params
        internal var enabled = false
        internal var actionChar = KeystrokeActionChar.NONE
        internal var delayControlChar = 0
        internal var characterDelay = 0
        internal var delayMultiByteCharsOnly = false
        internal var sendCharsAsEvents = false
        internal var sendControlCharsAsEvents = false
        internal var sendTabAsString = false

        // Builders
        fun resetConfig(resetConfig: Boolean): Builder =
            apply { this.resetConfig = resetConfig }

        fun setEnabled(enabled: Boolean): Builder =
            apply { this.enabled = enabled }

        fun setKeystrokeActionChar(actionChar: KeystrokeActionChar): Builder =
            apply { this.actionChar = actionChar }

        fun setDelayControlChar(delayControlChar: Int): Builder =
            apply { this.delayControlChar = delayControlChar }

        fun setCharacterDelay(characterDelay: Int): Builder =
            apply { this.characterDelay = characterDelay }

        fun setDelayMultiByteCharsOnly(delayMultiByteCharsOnly: Boolean): Builder =
            apply { this.delayMultiByteCharsOnly = delayMultiByteCharsOnly }

        fun setSendCharsAsEvents(sendCharsAsEvents: Boolean): Builder =
            apply { this.sendCharsAsEvents = sendCharsAsEvents }

        fun setSendControlCharsAsEvents(sendControlCharsAsEvents: Boolean): Builder =
            apply { this.sendControlCharsAsEvents = sendControlCharsAsEvents }

        fun setSendTabAsString(sendTabAsString: Boolean): Builder =
            apply { this.sendTabAsString = sendTabAsString }

        fun create(): Bundle {
            return KeystrokeOutputPlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "KEYSTROKE"

        private const val KEYSTROKE_OUTPUT_ENABLED_KEY = "keystroke_output_enabled"
        private const val KEYSTROKE_ACTION_CHAR_KEY = "keystroke_action_char"
        private const val KEYSTROKE_DELAY_CONTROL_KEY = "keystroke_delay_control_char"
        private const val KEYSTROKE_CHARACTER_DELAY_KEY = "keystroke_character_delay"
        private const val KEYSTROKE_DELAY_MULTIBYTE_CHARS_ONLY_KEY =
            "keystroke_delay_multibyte_chars_only"
        private const val KEYSTROKE_SEND_CHARS_AS_EVENTS_KEY = "keystroke_send_chars_as_events"
        private const val KEYSTROKE_SEND_CONTROL_CHARS_AS_EVENTS_KEY =
            "keystroke_send_control_chars_as_events"
        private const val KEYSTROKE_SEND_TAB_AS_STRING_KEY = "keystroke_send_tab_as_string"
    }
}