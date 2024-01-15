package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.barcode.BarcodeHighlightGenericRule
import java.util.Locale

class BarcodePlugin private constructor(builder: Builder) {

    // Scanner Enum
    enum class ScannerIdentifier {
        AUTO, INTERNAL_IMAGER, INTERNAL_LASER, INTERNAL_CAMERA, SERIAL_SSI, BLUETOOTH_SSI, BLUETOOTH_RS6000, BLUETOOTH_DS2278, BLUETOOTH_DS3678, PLUGABLE_SSI, PLUGABLE_SSI_RS5000, USB_SSI_DS3608
    }

    enum class ReaderAimType {
        TRIGGER, TIMED_HOLD, TIMED_RELEASE, PRESS_AND_RELEASE, PRESENTATION, CONTINUOUS_READ, PRESS_AND_SUSTAIN, PRESS_AND_CONTINUE
    }

    // Illumination Mode Enum
    enum class ScannerIlluminationMode {
        OFF, TORCH
    }

    // Bundle
    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle()
        paramList.putString(SCANNER_ENABLED_KEY, if (builder.mEnabled) "true" else "false")
        paramList.putString(SCANNER_SELECTION_KEY, builder.scannerIdentifier.name)
        paramList.putString(SCANNER_ILLUMINATION_MODE, builder.scannerIlluminationMode.name)
        paramList.putString(
            SCANNER_ILLUMINATION_BRIGHTNESS,
            builder.scannerIlluminationBrightness.toString()
        )
        paramList.putString(
            READER_AIM_TYPE,
            String.format(Locale.getDefault(), "%d", builder.readerAimType.ordinal)
        )

        // Other
        paramList.putString(
            DECODE_HAPTIC_FEEDBACK_KEY,
            if (builder.decodeHapticFeedback) "true" else "false"
        )
        paramList.putString(
            SCAN_HARDWARE_TRIGGER,
            if (builder.hardwareTrigger) "1" else "0"
        )

        //Highlight
        paramList.putString(
            HIGHLIGHT_ENABLE_KEY,
            if (builder.enableBarcodeHighlight) "true" else "false"
        )

        //Highlight Overlay Part
        val barcodeOverlayRuleList: ArrayList<Bundle> = ArrayList()
        for (overlayRule in builder.overlayRules) {
            val ruleBundle = Bundle().apply {
                putString("rule_name", overlayRule.name)
                putBundle("criteria", Bundle().apply {
                    putParcelableArrayList("identifier", overlayRule.getCriteriaListBundle())
                    putStringArray("symbology", overlayRule.getSymbologiesArray())
                })
                putParcelableArrayList("actions", overlayRule.getActionListBundle())
            }
            barcodeOverlayRuleList.add(ruleBundle)
        }

        //Highlight Report Part
        val reportDataRuleList: ArrayList<Bundle> = ArrayList()
        for (reportDataRule in builder.reportDataRules) {
            val ruleBundle = Bundle().apply {
                putString("rule_name", reportDataRule.name)
                putBundle("criteria", Bundle().apply {
                    putParcelableArrayList("identifier", reportDataRule.getCriteriaListBundle())
                    putStringArray("symbology", reportDataRule.getSymbologiesArray())
                })
                putParcelableArrayList("actions", reportDataRule.getActionListBundle())
            }
            reportDataRuleList.add(ruleBundle)
        }

        val highlightingRules: ArrayList<Bundle> = ArrayList<Bundle>().apply {
            add(
                Bundle().apply {
                    putString("rule_param_id", "barcode_overlay")
                    putParcelableArrayList("rule_list", barcodeOverlayRuleList)
                }
            )
            add(
                Bundle().apply {
                    putString("rule_param_id", "report_data")
                    putParcelableArrayList("rule_list", reportDataRuleList)
                }
            )
        }
        paramList.putParcelableArrayList("barcode_highlighting_rules", highlightingRules)

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
    }

    class Builder {
        // Config
        internal var resetConfig = true

        // Params
        internal var mEnabled = false
        internal var scannerIdentifier = ScannerIdentifier.AUTO

        // Reader Params
        internal var readerAimType = ReaderAimType.TRIGGER

        // Other
        internal var decodeHapticFeedback = false
        internal var scannerIlluminationMode = ScannerIlluminationMode.OFF
        internal var scannerIlluminationBrightness = 0
        internal var hardwareTrigger = true

        //Highlight
        internal var enableBarcodeHighlight = false
        internal val overlayRules = ArrayList<BarcodeHighlightGenericRule>()
        internal val reportDataRules = ArrayList<BarcodeHighlightGenericRule>()

        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.mEnabled = enabled
            return this
        }

        fun setScannerIdentifier(scannerIdentifier: ScannerIdentifier): Builder {
            this.scannerIdentifier = scannerIdentifier
            return this
        }

        fun setReaderAimType(readerAimType: ReaderAimType): Builder {
            this.readerAimType = readerAimType
            return this
        }

        fun setDecodeHapticFeedback(decodeHapticFeedback: Boolean): Builder {
            this.decodeHapticFeedback = decodeHapticFeedback
            return this
        }

        fun setScannerIlluminationMode(
            scannerIlluminationMode: ScannerIlluminationMode
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

        fun setEnableBarcodeHighlight(): Builder {
            enableBarcodeHighlight = true
            return this
        }

        fun addNewBarcodeHighlightOverlayRule(rule: BarcodeHighlightGenericRule): Builder {
            overlayRules.add(rule)
            return this
        }

        fun addNewBarcodeHighlightReportDataRule(rule: BarcodeHighlightGenericRule): Builder {
            reportDataRules.add(rule)
            return this
        }

        fun setScannerIlluminationBrightness(illuminationBrightness: Int): Builder {
            var scannerIlluminationBrightness = illuminationBrightness
            if (scannerIlluminationBrightness < 0) scannerIlluminationBrightness = 0
            if (scannerIlluminationBrightness > 10) scannerIlluminationBrightness = 10
            this.scannerIlluminationBrightness = scannerIlluminationBrightness

            return this
        }

        fun create(): Bundle {
            return BarcodePlugin(this).plugin
        }
    }

    companion object {
        // Plugin Parameter Names
        private const val PLUGIN_NAME = "BARCODE"
        private const val SCANNER_ENABLED_KEY = "scanner_input_enabled"
        private const val SCANNER_SELECTION_KEY = "scanner_selection_by_identifier"

        // Other Scanner Input Parameters
        private const val SCANNER_ILLUMINATION_MODE = "illumination_mode"
        private const val SCANNER_ILLUMINATION_BRIGHTNESS = "illumination_brightness"

        // Reader Parameters
        private const val READER_AIM_TYPE = "aim_type"

        // TODO: DECODERS

        // TODO: OCR

        // Other
        private const val DECODE_HAPTIC_FEEDBACK_KEY = "decode_haptic_feedback"
        private const val SCAN_HARDWARE_TRIGGER = "barcode_trigger_mode"

        // Highlight
        private const val HIGHLIGHT_ENABLE_KEY = "barcode_highlighting_enabled"
    }
}