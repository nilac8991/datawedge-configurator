package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.keystroke.KeystrokeActionChar

class KeystrokeOutputPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle().apply {
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

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
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
        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.enabled = enabled
            return this
        }

        fun setKeystrokeActionChar(actionChar: KeystrokeActionChar): Builder {
            this.actionChar = actionChar
            return this
        }

        fun setDelayControlChar(delayControlChar: Int): Builder {
            this.delayControlChar = delayControlChar
            return this
        }

        fun setCharacterDelay(characterDelay: Int): Builder {
            this.characterDelay = characterDelay
            return this
        }

        fun setDelayMultiByteCharsOnly(delayMultiByteCharsOnly: Boolean): Builder {
            this.delayMultiByteCharsOnly = delayMultiByteCharsOnly
            return this
        }

        fun setSendCharsAsEvents(sendCharsAsEvents: Boolean): Builder {
            this.sendCharsAsEvents = sendCharsAsEvents
            return this
        }

        fun setSendControlCharsAsEvents(sendControlCharsAsEvents: Boolean): Builder {
            this.sendControlCharsAsEvents = sendControlCharsAsEvents
            return this
        }

        fun setSendTabAsString(sendTabAsString: Boolean): Builder {
            this.sendTabAsString = sendTabAsString
            return this
        }

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