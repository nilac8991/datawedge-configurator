package com.zebra.nilac.dwconfigurator.configuration.params.barcode

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.barcode.scan.ScanBeepVolumeChannelType
import com.zebra.nilac.dwconfigurator.models.barcode.scan.ScanCodeIdType
import java.util.Locale

class ScanParams private constructor(builder: Builder) {

    private val params = Bundle()

    init {
        params.apply {
            putString(
                CODE_ID_TYPE_KEY, String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.codeIdType.ordinal
                )
            )
            putString(
                DECODE_HAPTIC_FEEDBACK_KEY,
                if (builder.decodeHapticFeedback) "true" else "false"
            )
            putString(
                DECODE_AUDIO_FEEDBACK_KEY,
                builder.decodeHapticFeedbackAudioUri
            )
            putString(
                DECODE_SCREEN_NOTIFICATION_KEY,
                if (builder.decodeScreenNotificationEnabled) "true" else "false"
            )
            putString(
                DECODE_SCREEN_NOTIFICATION_TIMER_KEY,
                builder.decodeScreenNotificationTimer.toString()
            )
            putString(
                DECODE_SCREEN_TRANSLUCENCY_LEVEL_KEY,
                builder.decodeScreenTranslucencyLevel.toString()
            )
            putString(
                DECODE_LED_NOTIFICATION_KEY,
                if (builder.decodeLedNotificationEnabled) "true" else "false"
            )
            putString(
                DECODE_LED_FEEDBACK_TIMER_KEY,
                builder.decodeLedNotificationFeedbackTimer.toString()
            )
            putString(
                BEEP_VOLUME_CHANNEL_KEY, String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.beepVolumeChannelType.ordinal
                )
            )
        }
    }

    class Builder {
        internal var codeIdType = ScanCodeIdType.NONE
        internal var decodeHapticFeedback = false
        internal var decodeHapticFeedbackAudioUri = "optimized-beep"
        internal var decodeScreenNotificationEnabled = false
        internal var decodeScreenNotificationTimer = 1000
        internal var decodeScreenTranslucencyLevel = 35
        internal var decodeLedNotificationEnabled = false
        internal var decodeLedNotificationFeedbackTimer = 75
        internal var beepVolumeChannelType = ScanBeepVolumeChannelType.NOTIFICATIONS

        fun setCodeIdType(type: ScanCodeIdType): Builder =
            apply { this.codeIdType = type }

        fun setDecodeHapticFeedbackEnabled(state: Boolean): Builder =
            apply { this.decodeHapticFeedback = state }

        fun setDecodeHapticFeedbackAudioUri(dwAudioUri: String): Builder =
            apply { this.decodeHapticFeedbackAudioUri = dwAudioUri }

        fun setDecodeScreenNotificationEnabled(state: Boolean): Builder =
            apply { this.decodeScreenNotificationEnabled = state }

        fun setDecodeScreenNotificationTimer(value: Int): Builder =
            apply { this.decodeScreenNotificationTimer = value }

        fun setDecodeScreenTranslucencyLevel(level: Int): Builder =
            apply { this.decodeScreenTranslucencyLevel = level }

        fun setDecodeLedNotificationEnabled(state: Boolean): Builder =
            apply { this.decodeLedNotificationEnabled = state }

        fun setDecodeLedNotificationFeedbackTimer(value: Int): Builder =
            apply { this.decodeLedNotificationFeedbackTimer = value }

        fun setBeepVolumeChannelType(type: ScanBeepVolumeChannelType): Builder =
            apply { this.beepVolumeChannelType = type }

        fun create(): Bundle {
            return ScanParams(this).params
        }
    }

    companion object {
        internal const val CODE_ID_TYPE_KEY = "code_id_type"
        internal const val DECODE_HAPTIC_FEEDBACK_KEY = "decode_haptic_feedback"
        internal const val DECODE_AUDIO_FEEDBACK_KEY = "decode_audio_feedback_uri"
        internal const val DECODE_SCREEN_NOTIFICATION_KEY = "decode_screen_notification"
        internal const val DECODE_SCREEN_NOTIFICATION_TIMER_KEY = "decode_screen_time"
        internal const val DECODE_SCREEN_TRANSLUCENCY_LEVEL_KEY = "decode_screen_translucency"
        internal const val DECODE_LED_NOTIFICATION_KEY = "decoding_led_feedback"
        internal const val DECODE_LED_FEEDBACK_TIMER_KEY = "good_decode_led_timer"
        internal const val BEEP_VOLUME_CHANNEL_KEY = "volume_slider_type"
    }
}