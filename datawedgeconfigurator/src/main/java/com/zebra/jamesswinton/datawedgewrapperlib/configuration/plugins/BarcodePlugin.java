package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins;

import android.os.Bundle;

import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants;

public class BarcodePlugin {

    // Scanner Enum
    public enum ScannerIdentifier { AUTO, INTERNAL_IMAGER, INTERNAL_LASER, INTERNAL_CAMERA,
        SERIAL_SSI, BLUETOOTH_SSI, BLUETOOTH_RS6000, BLUETOOTH_DS2278, BLUETOOTH_DS3678,
        PLUGABLE_SSI, PLUGABLE_SSI_RS5000, USB_SSI_DS3608, }

    // Plugin Parameter Names
    private static final String PLUGIN_NAME = "BARCODE";
    private static final String SCANNER_ENABLED_KEY = "scanner_input_enabled";
    private static final String SCANNER_SELECTION_KEY = "scanner_selection_by_identifier";

    // TODO: DECODERS

    // TODO: OCR

    // Other
    private static final String DECODE_HAPTIC_FEEDBACK_KEY = "decode_haptic_feedback";


    // Bundle
    private Bundle plugin = new Bundle();

    //
    private BarcodePlugin(Builder builder) {
        // Build Params
        Bundle paramList = new Bundle();
        paramList.putString(SCANNER_ENABLED_KEY, builder.enabled ? "true" : "false");
        paramList.putString(SCANNER_SELECTION_KEY, builder.scannerIdentifier.name());

        // Other
        paramList.putString(DECODE_HAPTIC_FEEDBACK_KEY, builder.decodeHapticFeedback
                ? "true" : "false");

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME);
        plugin.putString("RESET_CONFIG", builder.resetConfig ? "true" : "false");
        plugin.putBundle("PARAM_LIST", paramList);
    }

    private Bundle getPlugin() {
        return plugin;
    }

    public static class Builder {

        // Config
        private boolean resetConfig = true;

        // Params
        private boolean enabled = false;
        private ScannerIdentifier scannerIdentifier = ScannerIdentifier.AUTO;

        // Other
        private boolean decodeHapticFeedback = false;

        public Builder resetConfig(boolean resetConfig) {
            this.resetConfig = resetConfig;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setScannerIdentifier(ScannerIdentifier scannerIdentifier) {
            this.scannerIdentifier = scannerIdentifier;
            return this;
        }

        public Builder setDecodeHapticFeedback(boolean decodeHapticFeedback) {
            this.decodeHapticFeedback = decodeHapticFeedback;
            return this;
        }

        public Bundle create() {
            return new BarcodePlugin(this).getPlugin();
        }
    }
}
