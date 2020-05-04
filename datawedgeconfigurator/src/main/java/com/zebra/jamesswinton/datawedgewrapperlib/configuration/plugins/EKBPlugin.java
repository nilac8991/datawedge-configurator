package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins;

import android.os.Bundle;

public class EKBPlugin {

    private static final String EKB_ENABLED_KEY = "ekb_enabled";
    private static final String EKB_LAYOUT_KEY = "ekb_layout";

    private Bundle plugin = new Bundle();

    private EKBPlugin(Builder builder) {
        plugin.putString(EKB_ENABLED_KEY, builder.enabled ? "true" : "false");
        plugin.putBundle(EKB_LAYOUT_KEY, builder.layout);
    }

    private Bundle getPlugin() {
        return plugin;
    }

    public static class Builder {

        private Boolean enabled = false;
        private Bundle layout = null;

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setLayout(String layoutGroup, String layoutName) {
            Bundle layoutBundle = new Bundle();
            layoutBundle.putString("layout_group", layoutGroup);
            layoutBundle.putString("layout_name ", layoutName);
            this.layout = layoutBundle;
            return this;
        }

        public Bundle create() {
            return new EKBPlugin(this).getPlugin();
        }

    }
}
