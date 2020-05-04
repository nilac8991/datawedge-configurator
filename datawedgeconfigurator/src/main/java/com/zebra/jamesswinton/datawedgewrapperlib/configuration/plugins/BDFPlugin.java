package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins;

import android.os.Bundle;

import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants;

public class BDFPlugin {

    // Plugin Parameter Names
    private static final String PLUGIN_NAME = "BDF";
    private static final String BDF_ENABLED_KEY = "bdf_enabled";
    private static final String BDF_PREFIX_KEY = "bdf_prefix";
    private static final String BDF_SUFFIX_KEY = "bdf_suffix";
    private static final String BDF_SEND_DATA_KEY = "bdf_send_data";
    private static final String BDF_SEND_HEX_KEY = "bdf_send_hex";
    private static final String BDF_SEND_TAB_KEY = "bdf_send_tab";
    private static final String BDF_SEND_ENTER_KEY = "bdf_send_enter";

    // Bundle
    private Bundle plugin = new Bundle();

    //
    private BDFPlugin(Builder builder) {
        // Build Params
        Bundle paramList = new Bundle();
        paramList.putString(BDF_ENABLED_KEY, builder.enabled ? "true" : "false");
        paramList.putString(BDF_PREFIX_KEY, builder.prefix);
        paramList.putString(BDF_SUFFIX_KEY, builder.suffix);
        paramList.putString(BDF_SEND_DATA_KEY, builder.sendData ? "true" : "false");
        paramList.putString(BDF_SEND_HEX_KEY, builder.sendHex ? "true" : "false");
        paramList.putString(BDF_SEND_TAB_KEY, builder.sendTab ? "true" : "false");
        paramList.putString(BDF_SEND_ENTER_KEY, builder.sendEnter ? "true" : "false");

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME);
        plugin.putString("RESET_CONFIG", builder.resetConfig ? "true" : "false");
        plugin.putBundle("PARAM_LIST", paramList);
        plugin.putString("OUTPUT_PLUGIN_NAME", builder.outputPluginName.name());
    }

    private Bundle getPlugin() {
        return plugin;
    }

    public static class Builder {

        // Config
        private boolean resetConfig = true;
        private Constants.OutputPluginName outputPluginName =
                Constants.OutputPluginName.INTENT;

        // Params
        private boolean enabled = false;
        private String prefix = "";
        private String suffix = "";
        private boolean sendData = false;
        private boolean sendHex = false;
        private boolean sendTab = false;
        private boolean sendEnter = false;

        public Builder resetConfig(boolean resetConfig) {
            this.resetConfig = resetConfig;
            return this;
        }

        public Builder setOutputPluginName(Constants.OutputPluginName outputPluginName) {
            this.outputPluginName = outputPluginName;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder setSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        public Builder sendData(boolean sendData) {
            this.sendData = sendData;
            return this;
        }

        public Builder sendHex(boolean sendHex) {
            this.sendHex = sendHex;
            return this;
        }

        public Builder sendTab(boolean sendTab) {
            this.sendTab = sendTab;
            return this;
        }

        public Builder sendEnter(boolean sendEnter) {
            this.sendEnter = sendEnter;
            return this;
        }

        public Bundle create() {
            return new BDFPlugin(this).getPlugin();
        }
    }
}
