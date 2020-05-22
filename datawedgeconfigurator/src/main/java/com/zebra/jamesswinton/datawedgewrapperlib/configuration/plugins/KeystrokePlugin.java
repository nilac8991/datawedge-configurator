package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins;

import android.os.Bundle;

import static com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants.*;

public class KeystrokePlugin {

    private static final String PLUGIN_NAME = "KEYSTROKE";
    private static final String KEYSTROKE_OUTPUT_ENABLED_KEY = "keystroke_output_enabled";
    private static final String KEYSTROKE_ACTION_CHAR_KEY = "keystroke_action_char";
    private static final String KEYSTROKE_DELAY_CONTROL_KEY = "keystroke_delay_control_char";
    private static final String KEYSTROKE_CHARACTER_DELAY_KEY = "keystroke_character_delay";
    private static final String KEYSTROKE_DELAY_MULTIBYTE_CHARS_ONLY_KEY
            = "keystroke_delay_multibyte_chars_only";
    private static final String KEYSTROKE_SEND_CHARS_AS_EVENTS_KEY
            = "keystroke_send_chars_as_events";
    private static final String KEYSTROKE_SEND_CONTROL_CHARS_AS_EVENTS_KEY
            = "keystroke_send_control_chars_as_events";
    private static final String KEYSTROKE_SEND_TAB_AS_STRING_KEY = "keystroke_send_tab_as_string";

    private Bundle plugin = new Bundle();

    private KeystrokePlugin(Builder builder) {
        // Build Params
        Bundle paramList = new Bundle();
        paramList.putString(KEYSTROKE_OUTPUT_ENABLED_KEY, builder.enabled ? "true" : "false");
        paramList.putString(KEYSTROKE_ACTION_CHAR_KEY, builder.actionChar.name());
        paramList.putString(KEYSTROKE_DELAY_CONTROL_KEY, String.valueOf(builder.delayControlChar));
        paramList.putString(KEYSTROKE_CHARACTER_DELAY_KEY, String.valueOf(builder.characterDelay));
        paramList.putString(KEYSTROKE_DELAY_MULTIBYTE_CHARS_ONLY_KEY,
                builder.delayMultiByteCharsOnly ? "true" : "false");
        paramList.putString(KEYSTROKE_SEND_CHARS_AS_EVENTS_KEY,
                builder.sendCharsAsEvents ? "true" : "false");
        paramList.putString(KEYSTROKE_SEND_CONTROL_CHARS_AS_EVENTS_KEY,
                builder.sendControlCharsAsEvents ? "true" : "false");
        paramList.putString(KEYSTROKE_SEND_TAB_AS_STRING_KEY,
                builder.sendTabAsString ? "true" : "false");

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
        private KeystrokeActionChar actionChar = KeystrokeActionChar.ASCII_NO_VALUE;
        private int delayControlChar = 0;
        private int characterDelay = 0;
        private boolean delayMultiByteCharsOnly = false;
        private boolean sendCharsAsEvents = false;
        private boolean sendControlCharsAsEvents = false;
        private boolean sendTabAsString = false;


        // Builders
        public Builder resetConfig(boolean resetConfig) {
            this.resetConfig = resetConfig;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setKeystrokeActionChar(KeystrokeActionChar actionChar) {
            this.actionChar = actionChar;
            return this;
        }

        public Builder setDelayControlChar(int delayControlChar) {
            this.delayControlChar = delayControlChar;
            return this;
        }

        public Builder setCharacterDelay(int characterDelay) {
            this.characterDelay = characterDelay;
            return this;
        }

        public Builder setDelayMultiByteCharsOnly(boolean delayMultiByteCharsOnly) {
            this.delayMultiByteCharsOnly = delayMultiByteCharsOnly;
            return this;
        }

        public Builder setSendCharsAsEvents(boolean sendCharsAsEvents) {
            this.sendCharsAsEvents = sendCharsAsEvents;
            return this;
        }

        public Builder setSendControlCharsAsEvents(boolean sendControlCharsAsEvents) {
            this.sendControlCharsAsEvents = sendControlCharsAsEvents;
            return this;
        }

        public Builder setSendTabAsString(boolean sendTabAsString) {
            this.sendTabAsString = sendTabAsString;
            return this;
        }

        public Bundle create() {
            return new KeystrokePlugin(this).getPlugin();
        }
    }
}
