package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.configuration.params.barcode.HighlightingParams
import com.zebra.nilac.dwconfigurator.configuration.params.barcode.ReaderParams
import com.zebra.nilac.dwconfigurator.configuration.params.barcode.UPCEANParams
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeAutoSwitchEventMode
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeScannerIdentifier
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeScanningMode
import com.zebra.nilac.dwconfigurator.models.barcode.BarcodeSymbology
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

        //Reader Params
        for (key in builder.readerParams.keySet()) {
            val value: String = builder.readerParams.getString(key)!!
            paramList.putString(key, value)
        }

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
            HighlightingParams.HIGHLIGHT_ENABLE_KEY,
            builder.barcodeHighlightingParams.getString(HighlightingParams.HIGHLIGHT_ENABLE_KEY)
        )
        paramList.putParcelableArrayList(
            HighlightingParams.HIGHLIGHT_RULES_KEY,
            builder.barcodeHighlightingParams.getParcelableArrayList(HighlightingParams.HIGHLIGHT_RULES_KEY)
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

        //Reader Params
        internal var readerParams = ReaderParams.Builder().create()

        internal var decodeHapticFeedback = false

        //Symbologies
        internal var symbologiesToEnable = ArrayList<BarcodeSymbology>()
        internal var symbologiesToDisable = ArrayList<BarcodeSymbology>()

        //Highlight
        internal var barcodeHighlightingParams = HighlightingParams.Builder().create()

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

        fun addReaderParams(params: Bundle): Builder {
            this.readerParams = params
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

        // Other
        private const val DECODE_HAPTIC_FEEDBACK_KEY = "decode_haptic_feedback"
    }
}