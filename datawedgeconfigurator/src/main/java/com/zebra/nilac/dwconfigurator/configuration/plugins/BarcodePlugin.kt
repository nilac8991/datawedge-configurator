package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.configuration.params.BarcodeHighlightingParams
import com.zebra.nilac.dwconfigurator.configuration.params.UPCEANParams
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeAutoSwitchEventMode
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeScannerIdentifier
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeScanningMode
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeSymbology
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

// TODO: OCR
// TODO: NG SimulScan Support
class BarcodePlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle()
        paramList.putString(SCANNER_ENABLED_KEY, if (builder.mEnabled) "true" else "false")
        paramList.putString(SCANNER_SELECTION_KEY, builder.scannerIdentifier.name)

        if (builder.autoSwitchToDefaultOnEvent != null) {
            paramList.putString(
                AUTO_SWITCH_TO_DEFAULT_ON_EVENT_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.autoSwitchToDefaultOnEvent!!.ordinal
                )
            )
        }

        paramList.putString(
            SCAN_HARDWARE_TRIGGER_KEY,
            if (builder.hardwareTrigger) "1" else "0"
        )

        //QRCode Launch Options
        paramList.putString(
            QR_CODE_LAUNCH_OPTIONS_ENABLE_KEY,
            if (builder.qrCodeLaunchOptionsEnable) "true" else "false"
        )
        paramList.putString(
            QR_CODE_LAUNCH_OPTIONS_ENABLE_DECODER_KEY,
            if (builder.qrCodeLaunchOptionsEnableDecoder) "true" else "false"
        )
        paramList.putString(
            QR_CODE_LAUNCH_OPTIONS_SHOW_CONFIRMATION_KEY,
            if (builder.qrCodeLaunchOptionsShowConfirmation) "true" else "false"
        )

        //Scanning Mode
        paramList.putString(
            SCANNING_MODE_KEY,
            String.format(Locale.getDefault(), "%d", builder.scanningMode.ordinal)
        )

        //Character Set Configuration
        paramList.putString(CHARACTER_SET_SELECTION_KEY, builder.charsetName.charset)
        paramList.putString(
            AUTO_CHARACTER_SET_PREFERRED_ORDER_KEY,
            builder.autoCharsetPreferredOrder.joinToString(separator = ";")
        )
        paramList.putString(
            AUTO_CHARSET_FAILURE_OPTION_KEY,
            builder.autoCharsetFailureOption.charset
        )

        //Misc Reader Parameters
        paramList.putString(
            PRESENTATION_MODE_SENSITIVITY_KEY,
            builder.presentationModeSensitivity.mode.toString()
        )
        paramList.putString(
            QUIET_ZONE_LEVEL_1D_KEY,
            String.format(Locale.getDefault(), "%d", builder.quietZone1DLevel.ordinal)
        )

        paramList.putString(READER_AIM_ENABLED_KEY, if (builder.aimModeEnabled) "on" else "off")
        paramList.putString(
            READER_AIM_TYPE_KEY,
            String.format(Locale.getDefault(), "%d", builder.readerAimType.ordinal)
        )
        paramList.putString(READER_AIM_TIMER_KEY, builder.aimTimer.toString())

        paramList.putString(BEAM_TIMER_KEY, builder.beamTimer.toString())

        paramList.putString(DIFFERENT_SYMBOL_TIMEOUT_KEY, builder.differentSymbolTimeout.toString())
        paramList.putString(SAME_SYMBOL_TIMEOUT_KEY, builder.sameSymbolTimeout.toString())

        paramList.putString(
            DIGIMARC_DECODING_KEY,
            if (builder.digimarcDecodingEnabled) "true" else "false"
        )
        paramList.putString(SCANNER_ILLUMINATION_MODE_KEY, builder.scannerIlluminationMode.name)
        paramList.putString(
            SCANNER_ILLUMINATION_BRIGHTNESS_KEY,
            builder.scannerIlluminationBrightness.toString()
        )

        paramList.putString(
            INVERSE_1D_MODE_KEY,
            String.format(Locale.getDefault(), "%d", builder.inverse1DMode.ordinal)
        )
        paramList.putString(
            LCD_MODE_KEY,
            builder.lcdMode.mode.toString()
        )
        paramList.putString(
            LINEAR_SECURITY_LEVEL_KEY,
            String.format(Locale.getDefault(), "%d", builder.linearSecurityLevel.ordinal + 1)
        )
        paramList.putString(
            HW_LOW_POWER_TIMEOUT_KEY,
            builder.hwEngineLowPowerTimeout.toString()
        )
        paramList.putString(
            TIMED_CONTINUOUS_BEAM_TIMER_KEY,
            builder.timedContinuousBeamTimer.toString()
        )
        paramList.putString(
            PICKLIST_KEY,
            String.format(Locale.getDefault(), "%d", builder.pickListMode.ordinal)
        )
        paramList.putString(
            POOR_QUALITY_DECODE_EFFORT_KEY,
            String.format(Locale.getDefault(), "%d", builder.poorQualityDecodeEffortLevel.ordinal)
        )
        paramList.putString(
            TRIGGER_WAKEUP_SCAN_KEY,
            if (builder.triggerWakeUpScanEnabled) "true" else "false"
        )

        paramList.putString(
            DECODE_HAPTIC_FEEDBACK_KEY,
            if (builder.decodeHapticFeedback) "true" else "false"
        )

        //Symbologies
        if (builder.symbologiesToEnable.size > 0) {
            for (barcodeSymbology in builder.symbologiesToEnable) {
                paramList.putString(barcodeSymbology.symbology, "true")
            }
        }
        if (builder.symbologiesToDisable.size > 0) {
            for (barcodeSymbology in builder.symbologiesToDisable) {
                paramList.putString(barcodeSymbology.symbology, "false")
            }
        }

        //Barcode Highlight
        paramList.putString(
            BarcodeHighlightingParams.HIGHLIGHT_ENABLE_KEY,
            builder.barcodeHighlightingParams.getString(BarcodeHighlightingParams.HIGHLIGHT_ENABLE_KEY)
        )
        paramList.putParcelableArrayList(
            BarcodeHighlightingParams.HIGHLIGHT_RULES_KEY,
            builder.barcodeHighlightingParams.getParcelableArrayList(BarcodeHighlightingParams.HIGHLIGHT_RULES_KEY)
        )

        //UPC EAN Params
        for (key in builder.upcEanParams.keySet()) {
            val value: String = builder.upcEanParams.getString(key)!!
            paramList.putString(key, value)
        }

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
    }

    class Builder {
        // Config
        internal var resetConfig = true

        internal var mEnabled = false
        internal var scannerIdentifier = BarcodeScannerIdentifier.AUTO

        //FIXME Weird issue when listening for DW responses returning wrongly results after setting this param even if the value is being set correctly. Keep it null unless it's being used.
        internal var autoSwitchToDefaultOnEvent: BarcodeAutoSwitchEventMode? = null

        internal var hardwareTrigger = true

        //QRCode Launch Options
        internal var qrCodeLaunchOptionsEnable = false
        internal var qrCodeLaunchOptionsEnableDecoder = false
        internal var qrCodeLaunchOptionsShowConfirmation = true

        //Scanning Mode
        internal var scanningMode = BarcodeScanningMode.SINGLE

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

        internal var decodeHapticFeedback = false

        //Symbologies
        internal var symbologiesToEnable = ArrayList<BarcodeSymbology>()
        internal var symbologiesToDisable = ArrayList<BarcodeSymbology>()

        //Highlight
        internal var barcodeHighlightingParams = BarcodeHighlightingParams.Builder().create()

        //UPC EAN Params
        internal var upcEanParams = UPCEANParams.Builder().create()

        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.mEnabled = enabled
            return this
        }

        fun setScannerIdentifier(scannerIdentifier: BarcodeScannerIdentifier): Builder {
            this.scannerIdentifier = scannerIdentifier
            return this
        }

        fun setDecodeHapticFeedback(decodeHapticFeedback: Boolean): Builder {
            this.decodeHapticFeedback = decodeHapticFeedback
            return this
        }

        fun setScannerIlluminationMode(
            scannerIlluminationMode: ReaderIlluminationMode
        ): Builder {
            this.scannerIlluminationMode = scannerIlluminationMode
            return this
        }

        fun enableScanHardwareTrigger(): Builder {
            this.hardwareTrigger = true
            return this
        }

        fun disableScanHardwareTrigger(): Builder {
            this.hardwareTrigger = false
            return this
        }

        fun setAutoSwitchToDefaultOnEvent(autoSwitchEventMode: BarcodeAutoSwitchEventMode): Builder {
            this.autoSwitchToDefaultOnEvent = autoSwitchEventMode
            return this
        }

        fun setQRCodeLaunchState(state: Boolean): Builder {
            qrCodeLaunchOptionsEnable = state
            return this
        }

        fun setQRCodeForceDecoderState(state: Boolean): Builder {
            qrCodeLaunchOptionsEnableDecoder = state
            return this
        }

        fun setQRCodeLaunchConfirmationState(state: Boolean): Builder {
            qrCodeLaunchOptionsShowConfirmation = state
            return this
        }

        fun setScanningMode(mode: BarcodeScanningMode): Builder {
            this.scanningMode = mode
            return this
        }

        fun setCharset(charsetName: ReaderCharsetName): Builder {
            this.charsetName = charsetName
            return this
        }

        fun setCharsetsPreferredOrder(charsets: Array<String>): Builder {
            this.autoCharsetPreferredOrder = charsets
            return this
        }

        fun setCharsetFailureOption(charsetName: ReaderCharsetName): Builder {
            this.autoCharsetFailureOption = charsetName
            return this
        }

        fun setPresentationModeSensitivity(sensitivity: ReaderPresentationModeSensitivity): Builder {
            this.presentationModeSensitivity = sensitivity
            return this
        }

        fun set1DQuietZoneLevel(level: ReaderQuietZone1DLevel): Builder {
            this.quietZone1DLevel = level
            return this
        }

        fun setReaderAimModeEnabled(state: Boolean): Builder {
            this.aimModeEnabled = state
            return this
        }

        fun setReaderAimType(readerAimType: ReaderAimType): Builder {
            this.readerAimType = readerAimType
            return this
        }

        fun setReaderAimTimer(value: Int): Builder {
            if (value < 0) this.aimTimer = 0
            if (value > 60000) this.aimTimer = 60000
            this.aimTimer = value

            return this
        }

        fun setBeamTimer(value: Int): Builder {
            if (value < 0) this.beamTimer = 0
            if (value > 60000) this.beamTimer = 60000
            this.beamTimer = value

            return this
        }

        fun setDifferentSymbolBarcodeTimeout(value: Int): Builder {
            if (value < 0) this.differentSymbolTimeout = 0
            if (value > 5000) this.differentSymbolTimeout = 5000
            this.differentSymbolTimeout = value

            return this
        }

        fun setDigimarcDecodingEnabled(state: Boolean): Builder {
            this.digimarcDecodingEnabled = state
            return this
        }

        fun setSameSymbolBarcodeTimeout(value: Int): Builder {
            if (value < 0) this.sameSymbolTimeout = 0
            if (value > 5000) this.sameSymbolTimeout = 5000
            this.sameSymbolTimeout = value

            return this
        }

        fun setScannerIlluminationBrightness(illuminationBrightness: Int): Builder {
            var scannerIlluminationBrightness = illuminationBrightness
            if (scannerIlluminationBrightness < 0) scannerIlluminationBrightness = 0
            if (scannerIlluminationBrightness > 10) scannerIlluminationBrightness = 10
            this.scannerIlluminationBrightness = scannerIlluminationBrightness

            return this
        }

        fun setInverse1DMode(mode: ReaderInverse1DMode): Builder {
            this.inverse1DMode = mode
            return this
        }

        fun setLCDMode(mode: ReaderLCDMode): Builder {
            this.lcdMode = mode
            return this
        }

        fun setLinearSecurityLevel(level: ReaderLinearSecurityLevel): Builder {
            this.linearSecurityLevel = level
            return this
        }

        fun setHwEngineLowPowerTimeout(value: Int): Builder {
            this.hwEngineLowPowerTimeout = value
            return this
        }

        fun setTimedContinuousBeamTimer(value: Int): Builder {
            this.timedContinuousBeamTimer = value
            return this
        }

        fun setPickListMode(mode: ReaderPickListMode): Builder {
            this.pickListMode = mode
            return this
        }

        fun setPoorQualityDecodeEffortLevel(level: ReaderPoorQualityDecodeEffortLevel): Builder {
            this.poorQualityDecodeEffortLevel = level
            return this
        }

        fun setTriggerWakeUpEnabled(state: Boolean): Builder {
            this.triggerWakeUpScanEnabled = state
            return this
        }

        fun enableSymbology(symbology: BarcodeSymbology): Builder {
            symbologiesToEnable.add(symbology)
            return this
        }

        fun enableSymbologies(symbologies: Array<BarcodeSymbology>): Builder {
            symbologiesToEnable.addAll(symbologies)
            return this
        }

        fun disableSymbology(symbology: BarcodeSymbology): Builder {
            symbologiesToDisable.add(symbology)
            return this
        }

        fun disableSymbologies(symbologies: Array<BarcodeSymbology>): Builder {
            symbologiesToDisable.addAll(symbologies)
            return this
        }

        fun addBarcodeHighlightingParams(params: Bundle): Builder {
            this.barcodeHighlightingParams = params
            return this
        }

        fun addUpcEanParams(params: Bundle): Builder {
            this.upcEanParams = params
            return this
        }

        fun create(): Bundle {
            return BarcodePlugin(this).plugin
        }
    }

    companion object {
        private const val PLUGIN_NAME = "BARCODE"

        private const val SCANNER_ENABLED_KEY = "scanner_input_enabled"
        private const val SCANNER_SELECTION_KEY = "scanner_selection_by_identifier"
        private const val AUTO_SWITCH_TO_DEFAULT_ON_EVENT_KEY = "auto_switch_to_default_on_event"
        private const val SCAN_HARDWARE_TRIGGER_KEY = "barcode_trigger_mode"

        //QRCode Launch Options
        private const val QR_CODE_LAUNCH_OPTIONS_ENABLE_KEY = "qr_launch_enable"
        private const val QR_CODE_LAUNCH_OPTIONS_ENABLE_DECODER_KEY = "qr_launch_enable_qr_decoder"
        private const val QR_CODE_LAUNCH_OPTIONS_SHOW_CONFIRMATION_KEY =
            "qr_launch_show_confirmation_dialog"

        //Scanning Mode
        private const val SCANNING_MODE_KEY = "scanning_mode"

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

        // Other
        private const val DECODE_HAPTIC_FEEDBACK_KEY = "decode_haptic_feedback"
    }
}