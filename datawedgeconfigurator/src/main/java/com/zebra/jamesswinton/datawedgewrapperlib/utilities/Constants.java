package com.zebra.jamesswinton.datawedgewrapperlib.utilities;

public class Constants {

    // Config Enums
    public enum ConfigMode { CREATE_IF_NOT_EXIST, OVERWRITE, UPDATE }
    public enum PluginName { BARCODE, MSR, RFID, SERIAL, SIMULSCAN, VOICE, BDF, ADF, TOKENS, INTENT, KEYSTROKE, IP, DCP, EKB }
    public enum OutputPluginName { INTENT, KEYSTROKE, IP }
    public enum KeystrokeActionChar { ASCII_NO_VALUE, ASCII_TAB_VALUE, ASCII_LF_VALUE, ASCII_CR_VALUE }

    // Intent Enums
    public enum IntentType { SET_CONFIG }
    public enum ResultType { NONE, LAST_RESULT, COMPLETE_RESULT }

    // Main Bundle Keys
    public static final String PROFILE_NAME_KEY = "PROFILE_NAME";
    public static final String CONFIG_MODE_KEY = "CONFIG_MODE";
    public static final String PROFILE_ENABLED_KEY = "PROFILE_ENABLED";
    public static final String PLUGIN_CONFIG_KEY  = "PLUGIN_CONFIG";
    public static final String APP_LIST_KEY  = "APP_LIST";

    // AppAssociations Bundle Keys
    public static final String PACKAGE_NAME_KEY = "PACKAGE_NAME";
    public static final String ACTIVITY_LIST_KEY = "ACTIVITY_LIST";


}
