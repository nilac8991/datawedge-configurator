package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.voice.VoiceDataCaptureStartOption
import com.zebra.nilac.dwconfigurator.models.voice.VoiceDataType
import java.util.Locale

class VoicePlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle().apply {
            putString(
                VOICE_INPUT_ENABLED_KEY,
                if (builder.enabled) "true" else "false"
            )
            putString(
                VOICE_DATA_CAPTURE_START_OPTION_KEY,
                String.format(Locale.getDefault(), "%d", builder.dataCaptureStartOption.ordinal)
            )
            putString(
                VOICE_END_DETECTION_TIMEOUT_KEY,
                builder.endDetectionTimeout.toString()
            )
            putString(
                VOICE_DATA_TYPE_KEY,
                String.format(Locale.getDefault(), "%d", builder.voiceDataType.ordinal)
            )
            putString(
                VOICE_DATA_CAPTURE_WAITING_TONE_KEY,
                if (builder.dataCaptureWaitingTone) "true" else "false"
            )
            putString(
                VOICE_VALIDATION_WINDOW_KEY,
                if (builder.validationWindow) "true" else "false"
            )
            putString(
                VOICE_OFFLINE_SPEECH_KEY,
                if (builder.offlineSpeech) "true" else "false"
            )
            putString(
                VOICE_COMMAND_TAB_ENABLED_KEY,
                if (builder.commandTabEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_TAB_PHRASE_KEY,
                builder.commandTabPhrase
            )
            putString(
                VOICE_COMMAND_ENTER_ENABLED_KEY,
                if (builder.commandEnterEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_ENTER_PHRASE_KEY,
                builder.commandEnterPhrase
            )
            putString(
                VOICE_COMMAND_MOVE_NEXT_ENABLED_KEY,
                if (builder.commandMoveNextEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_MOVE_NEXT_PHRASE_KEY,
                builder.commandMoveNextPhrase
            )
            putString(
                VOICE_COMMAND_MOVE_PREVIOUS_ENABLED_KEY,
                if (builder.commandMovePreviousEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_MOVE_PREVIOUS_PHRASE_KEY,
                builder.commandMovePreviousPhrase
            )
            putString(
                VOICE_COMMAND_ESCAPE_ENABLED_KEY,
                if (builder.commandEscapeEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_ESCAPE_PHRASE_KEY,
                builder.commandEscapePhrase
            )
            putString(
                VOICE_COMMAND_CLEAR_ENABLED_KEY,
                if (builder.commandClearEnabled) "true" else "false"
            )
            putString(
                VOICE_COMMAND_CLEAR_PHRASE_KEY,
                builder.commandClearPhrase
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
        internal var dataCaptureStartOption: VoiceDataCaptureStartOption =
            VoiceDataCaptureStartOption.NONE
        internal var endDetectionTimeout = 0
        internal var voiceDataType = VoiceDataType.ANY
        internal var dataCaptureWaitingTone = false
        internal var validationWindow = false
        internal var offlineSpeech = false

        internal var commandTabEnabled = false
        internal var commandTabPhrase = "send tab"
        internal var commandEnterEnabled = false
        internal var commandEnterPhrase = "send enter"
        internal var commandMoveNextEnabled = false
        internal var commandMoveNextPhrase = "move next"
        internal var commandMovePreviousEnabled = false
        internal var commandMovePreviousPhrase = "move previous"
        internal var commandEscapeEnabled = false
        internal var commandEscapePhrase = "send escape"
        internal var commandClearEnabled = false
        internal var commandClearPhrase = "clear"

        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.enabled = enabled
            return this
        }

        fun setDataCaptureStartOption(voiceDataCaptureStartOption: VoiceDataCaptureStartOption): Builder {
            this.dataCaptureStartOption = voiceDataCaptureStartOption
            return this
        }

        fun setEndDetectionTimeout(timeout: Int): Builder {
            this.endDetectionTimeout = if (timeout > 30) {
                30
            } else {
                timeout
            }
            return this
        }

        fun setVoiceDataType(voiceDataType: VoiceDataType): Builder {
            this.voiceDataType = voiceDataType
            return this
        }

        fun setDataCaptureWaitingTone(state: Boolean): Builder {
            this.dataCaptureWaitingTone = state
            return this
        }

        fun setValidationWindow(state: Boolean): Builder {
            this.validationWindow = state
            return this
        }

        fun setOfflineSpeech(state: Boolean): Builder {
            this.offlineSpeech = state
            return this
        }

        fun setCommandTabEnabled(state: Boolean): Builder {
            this.commandTabEnabled = state
            return this
        }

        fun setCommandTabPhrase(phrase: String): Builder {
            this.commandTabPhrase = phrase
            return this
        }

        fun setCommandEnterEnabled(state: Boolean): Builder {
            this.commandEnterEnabled = state
            return this
        }

        fun setCommandEnterPhrase(phrase: String): Builder {
            this.commandEnterPhrase = phrase
            return this
        }

        fun setCommandMoveNextEnabled(state: Boolean): Builder {
            this.commandMoveNextEnabled = state
            return this
        }

        fun setCommandMoveNextPhrase(phrase: String): Builder {
            this.commandMoveNextPhrase = phrase
            return this
        }

        fun setCommandMovePreviousEnabled(state: Boolean): Builder {
            this.commandMovePreviousEnabled = state
            return this
        }

        fun setCommandMovePreviousPhrase(phrase: String): Builder {
            this.commandMovePreviousPhrase = phrase
            return this
        }

        fun setCommandEscapeEnabled(state: Boolean): Builder {
            this.commandEscapeEnabled = state
            return this
        }

        fun setCommandEscapePhrase(phrase: String): Builder {
            this.commandEscapePhrase = phrase
            return this
        }

        fun setCommandClearEnabled(state: Boolean): Builder {
            this.commandClearEnabled = state
            return this
        }

        fun setCommandClearPhrase(phrase: String): Builder {
            this.commandClearPhrase = phrase
            return this
        }

        fun create(): Bundle {
            return VoicePlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "VOICE"

        private const val VOICE_INPUT_ENABLED_KEY = "voice_input_enabled"
        private const val VOICE_DATA_CAPTURE_START_OPTION_KEY = "voice_data_capture_start_option"
        private const val VOICE_END_DETECTION_TIMEOUT_KEY = "voice_end_detection_timeout"
        private const val VOICE_DATA_TYPE_KEY = "voice_data_type"
        private const val VOICE_DATA_CAPTURE_WAITING_TONE_KEY = "voice_data_capture_waiting_tone"
        private const val VOICE_VALIDATION_WINDOW_KEY = "voice_validation_window"
        private const val VOICE_OFFLINE_SPEECH_KEY = "voice_offline_speech"
        private const val VOICE_COMMAND_TAB_ENABLED_KEY = "voice_command_tab_enabled"
        private const val VOICE_COMMAND_TAB_PHRASE_KEY = "voice_command_tab_phrase"
        private const val VOICE_COMMAND_ENTER_ENABLED_KEY = "voice_command_enter_enabled"
        private const val VOICE_COMMAND_ENTER_PHRASE_KEY = "voice_command_enter_phrase"
        private const val VOICE_COMMAND_MOVE_NEXT_ENABLED_KEY = "voice_command_move_next_enabled"
        private const val VOICE_COMMAND_MOVE_NEXT_PHRASE_KEY = "voice_command_move_next_phrase"
        private const val VOICE_COMMAND_MOVE_PREVIOUS_ENABLED_KEY =
            "voice_command_move_previous_enabled"
        private const val VOICE_COMMAND_MOVE_PREVIOUS_PHRASE_KEY =
            "voice_command_move_previous_phrase"
        private const val VOICE_COMMAND_ESCAPE_ENABLED_KEY = "voice_command_escape_enabled"
        private const val VOICE_COMMAND_ESCAPE_PHRASE_KEY = "voice_command_escape_phrase"
        private const val VOICE_COMMAND_CLEAR_ENABLED_KEY = "voice_command_clear_enabled"
        private const val VOICE_COMMAND_CLEAR_PHRASE_KEY = "voice_command_clear_phrase"
    }
}