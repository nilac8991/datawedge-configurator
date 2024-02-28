package com.zebra.nilac.dwconfigurator.configuration.params.barcode

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderAimType
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderCharsetName
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderIlluminationMode
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderInverse1DMode
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderLCDMode
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderLinearSecurityLevel
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderPickListMode
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderPoorQualityDecodeEffortLevel
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderPresentationModeSensitivity
import com.zebra.nilac.dwconfigurator.models.barcode.reader.ReaderQuietZone1DLevel
import java.util.Locale

class ReaderParams private constructor(builder: Builder) {

    private val params = Bundle()

    init {
        params.apply {
            //Character Set Configuration
            putString(
                CHARACTER_SET_SELECTION_KEY,
                builder.charsetName.charset
            )
            putString(
                AUTO_CHARACTER_SET_PREFERRED_ORDER_KEY,
                builder.autoCharsetPreferredOrder.joinToString(separator = ";")
            )
            putString(
                AUTO_CHARSET_FAILURE_OPTION_KEY,
                builder.autoCharsetFailureOption.charset
            )

            //Misc Reader Parameters
            putString(
                PRESENTATION_MODE_SENSITIVITY_KEY,
                builder.presentationModeSensitivity.mode.toString()
            )
            putString(
                QUIET_ZONE_LEVEL_1D_KEY,
                String.format(Locale.getDefault(), "%d", builder.quietZone1DLevel.ordinal)
            )

            putString(
                READER_AIM_ENABLED_KEY,
                if (builder.aimModeEnabled) "on" else "off"
            )
            putString(
                READER_AIM_TYPE_KEY,
                String.format(Locale.getDefault(), "%d", builder.readerAimType.ordinal)
            )
            putString(
                READER_AIM_TIMER_KEY,
                builder.aimTimer.toString()
            )

            putString(
                BEAM_TIMER_KEY,
                builder.beamTimer.toString()
            )

            putString(
                DIFFERENT_SYMBOL_TIMEOUT_KEY,
                builder.differentSymbolTimeout.toString()
            )
            putString(
                SAME_SYMBOL_TIMEOUT_KEY,
                builder.sameSymbolTimeout.toString()
            )

            putString(
                DIGIMARC_DECODING_KEY,
                if (builder.digimarcDecodingEnabled) "true" else "false"
            )
            putString(
                SCANNER_ILLUMINATION_MODE_KEY,
                builder.scannerIlluminationMode.name
            )
            putString(
                SCANNER_ILLUMINATION_BRIGHTNESS_KEY,
                builder.scannerIlluminationBrightness.toString()
            )

            putString(
                INVERSE_1D_MODE_KEY,
                String.format(Locale.getDefault(), "%d", builder.inverse1DMode.ordinal)
            )
            putString(
                LCD_MODE_KEY,
                builder.lcdMode.mode.toString()
            )
            putString(
                LINEAR_SECURITY_LEVEL_KEY,
                String.format(Locale.getDefault(), "%d", builder.linearSecurityLevel.ordinal + 1)
            )
            putString(
                HW_LOW_POWER_TIMEOUT_KEY,
                builder.hwEngineLowPowerTimeout.toString()
            )
            putString(
                TIMED_CONTINUOUS_BEAM_TIMER_KEY,
                builder.timedContinuousBeamTimer.toString()
            )
            putString(
                PICKLIST_KEY,
                String.format(Locale.getDefault(), "%d", builder.pickListMode.ordinal)
            )
            putString(
                POOR_QUALITY_DECODE_EFFORT_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.poorQualityDecodeEffortLevel.ordinal
                )
            )
            putString(
                TRIGGER_WAKEUP_SCAN_KEY,
                if (builder.triggerWakeUpScanEnabled) "true" else "false"
            )
        }
    }

    class Builder {
        //Character Set Configuration
        internal var charsetName = ReaderCharsetName.UTF_8
        internal var autoCharsetPreferredOrder = arrayOf("UTF-8", "GB2312")
        internal var autoCharsetFailureOption = ReaderCharsetName.UTF_8

        //Misc Reader Params
        internal var presentationModeSensitivity = ReaderPresentationModeSensitivity.MEDIUM
        internal var quietZone1DLevel = ReaderQuietZone1DLevel.LEVEL_1

        internal var aimModeEnabled = true
        internal var readerAimType = ReaderAimType.TRIGGER
        internal var aimTimer = 500

        internal var beamTimer = 5000

        internal var differentSymbolTimeout = 500
        internal var sameSymbolTimeout = 500

        internal var digimarcDecodingEnabled = true

        internal var scannerIlluminationMode = ReaderIlluminationMode.OFF
        internal var scannerIlluminationBrightness = 0

        internal var inverse1DMode = ReaderInverse1DMode.DISABLE
        internal var lcdMode = ReaderLCDMode.DISABLED
        internal var linearSecurityLevel = ReaderLinearSecurityLevel.SHORT_OR_CODABAR
        internal var hwEngineLowPowerTimeout = 250
        internal var timedContinuousBeamTimer: Int = 180000
        internal var pickListMode = ReaderPickListMode.DISABLED
        internal var poorQualityDecodeEffortLevel = ReaderPoorQualityDecodeEffortLevel.LEVEL_0
        internal var triggerWakeUpScanEnabled = false

        fun setCharset(charsetName: ReaderCharsetName): Builder =
            apply { this.charsetName = charsetName }

        fun setCharsetsPreferredOrder(charsets: Array<String>): Builder =
            apply { this.autoCharsetPreferredOrder = charsets }

        fun setCharsetFailureOption(charsetName: ReaderCharsetName): Builder =
            apply { this.autoCharsetFailureOption = charsetName }

        fun setPresentationModeSensitivity(sensitivity: ReaderPresentationModeSensitivity): Builder =
            apply { this.presentationModeSensitivity = sensitivity }

        fun set1DQuietZoneLevel(level: ReaderQuietZone1DLevel): Builder =
            apply { this.quietZone1DLevel = level }

        fun setReaderAimModeEnabled(state: Boolean): Builder =
            apply { this.aimModeEnabled = state }

        fun setReaderAimType(readerAimType: ReaderAimType): Builder =
            apply { this.readerAimType = readerAimType }

        fun setReaderAimTimer(value: Int): Builder =
            apply {
                if (value < 0) this.aimTimer = 0
                if (value > 60000) this.aimTimer = 60000
                this.aimTimer = value
            }

        fun setBeamTimer(value: Int): Builder =
            apply {
                if (value < 0) this.beamTimer = 0
                if (value > 60000) this.beamTimer = 60000
                this.beamTimer = value
            }

        fun setDifferentSymbolBarcodeTimeout(value: Int): Builder =
            apply {
                if (value < 0) this.differentSymbolTimeout = 0
                if (value > 5000) this.differentSymbolTimeout = 5000
                this.differentSymbolTimeout = value
            }

        fun setDigimarcDecodingEnabled(state: Boolean): Builder =
            apply { this.digimarcDecodingEnabled = state }

        fun setSameSymbolBarcodeTimeout(value: Int): Builder =
            apply {
                if (value < 0) this.sameSymbolTimeout = 0
                if (value > 5000) this.sameSymbolTimeout = 5000
                this.sameSymbolTimeout = value
            }

        fun setScannerIlluminationMode(scannerIlluminationMode: ReaderIlluminationMode): Builder =
            apply { this.scannerIlluminationMode = scannerIlluminationMode }

        fun setScannerIlluminationBrightness(illuminationBrightness: Int): Builder =
            apply {
                var scannerIlluminationBrightness = illuminationBrightness
                if (scannerIlluminationBrightness < 0) scannerIlluminationBrightness = 0
                if (scannerIlluminationBrightness > 10) scannerIlluminationBrightness = 10
                this.scannerIlluminationBrightness = scannerIlluminationBrightness
            }

        fun setInverse1DMode(mode: ReaderInverse1DMode): Builder =
            apply { this.inverse1DMode = mode }

        fun setLCDMode(mode: ReaderLCDMode): Builder =
            apply { this.lcdMode = mode }

        fun setLinearSecurityLevel(level: ReaderLinearSecurityLevel): Builder =
            apply { this.linearSecurityLevel = level }

        fun setHwEngineLowPowerTimeout(value: Int): Builder =
            apply { this.hwEngineLowPowerTimeout = value }

        fun setTimedContinuousBeamTimer(value: Int): Builder =
            apply { this.timedContinuousBeamTimer = value }

        fun setPickListMode(mode: ReaderPickListMode): Builder =
            apply { this.pickListMode = mode }

        fun setPoorQualityDecodeEffortLevel(level: ReaderPoorQualityDecodeEffortLevel): Builder =
            apply { this.poorQualityDecodeEffortLevel = level }

        fun setTriggerWakeUpEnabled(state: Boolean): Builder =
            apply { this.triggerWakeUpScanEnabled = state }

        fun create(): Bundle {
            return ReaderParams(this).params
        }
    }

    companion object {
        // Character Set Configuration
        private const val CHARACTER_SET_SELECTION_KEY = "charset_name"
        private const val AUTO_CHARACTER_SET_PREFERRED_ORDER_KEY = "auto_charset_preferred_order"
        private const val AUTO_CHARSET_FAILURE_OPTION_KEY = "auto_charset_failure_option"

        // Misc Reader Parameters
        private const val PRESENTATION_MODE_SENSITIVITY_KEY = "presentation_mode_sensitivity"
        private const val QUIET_ZONE_LEVEL_1D_KEY = "1d_marginless_decode_effort_level"

        private const val READER_AIM_ENABLED_KEY = "aim_mode"
        private const val READER_AIM_TYPE_KEY = "aim_type"
        private const val READER_AIM_TIMER_KEY = "aim_timer"

        private const val BEAM_TIMER_KEY = "beam_timer"

        private const val DIFFERENT_SYMBOL_TIMEOUT_KEY = "different_barcode_timeout"
        private const val SAME_SYMBOL_TIMEOUT_KEY = "same_barcode_timeout"

        private const val DIGIMARC_DECODING_KEY = "digimarc_decoding"

        private const val SCANNER_ILLUMINATION_MODE_KEY = "illumination_mode"
        private const val SCANNER_ILLUMINATION_BRIGHTNESS_KEY = "illumination_brightness"

        private const val INVERSE_1D_MODE_KEY = "inverse_1d_mode"
        private const val LCD_MODE_KEY = "lcd_mode"
        private const val LINEAR_SECURITY_LEVEL_KEY = "linear_security_level"
        private const val HW_LOW_POWER_TIMEOUT_KEY = "low_power_timeout"
        private const val TIMED_CONTINUOUS_BEAM_TIMER_KEY = "nodecode_timer"
        private const val PICKLIST_KEY = "picklist"
        private const val POOR_QUALITY_DECODE_EFFORT_KEY = "poor_quality_bcdecode_effort_level"
        private const val TRIGGER_WAKEUP_SCAN_KEY = "trigger_wakeup_scan"
    }
}