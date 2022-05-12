package com.zebra.jamesswinton.datawedgewrapperlib.configuration;

import android.os.Bundle;

import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants;

import java.util.ArrayList;

import static com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants.ACTIVITY_LIST_KEY;
import static com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants.PACKAGE_NAME_KEY;

public class MainBundle {

    // Plugin Parameter Names
    private static final String PROFILE_NAME_KEY = "PROFILE_NAME";
    private static final String CONFIG_MODE_KEY = "CONFIG_MODE";
    private static final String PROFILE_ENABLED_KEY = "PROFILE_ENABLED";
    private static final String PLUGIN_CONFIG_KEY  = "PLUGIN_CONFIG";
    private static final String APP_LIST_KEY  = "APP_LIST";

    // Plugin
    private Bundle plugin = new Bundle();

    private MainBundle(Builder builder) {
        plugin.putString(PROFILE_NAME_KEY, builder.profileName);
        plugin.putString(CONFIG_MODE_KEY, builder.configMode.name());
        plugin.putString(PROFILE_ENABLED_KEY, builder.profileEnabled ? "true" : "false");
        plugin.putParcelableArrayList(PLUGIN_CONFIG_KEY, builder.pluginBundles);
        if (builder.appList.length > 0) {
            plugin.putParcelableArray(APP_LIST_KEY, builder.appList);
        }
    }

    private Bundle getPlugin() {
        return plugin;
    }

    public static class Builder {

        private String profileName = "ProfileNameNotSet";
        private Constants.ConfigMode configMode = Constants.ConfigMode.CREATE_IF_NOT_EXIST;
        private boolean profileEnabled = true;
        private ArrayList<Bundle> pluginBundles = new ArrayList<>();
        private Bundle[] appList = new Bundle[0];

        public Builder setProfileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        public Builder setConfigMode(Constants.ConfigMode configMode) {
            this.configMode = configMode;
            return this;
        }

        public Builder setProfileEnabled(boolean profileEnabled) {
            this.profileEnabled = profileEnabled;
            return this;
        }

        public Builder addPluginBundle(Bundle pluginBundle) {
            this.pluginBundles.add(pluginBundle);
            return this;
        }

        public Builder setPluginBundles(ArrayList<Bundle> pluginBundles) {
            this.pluginBundles = pluginBundles;
            return this;
        }

        public Builder setAppAssociations(Bundle[] appList) {
            this.appList = appList;
            return this;
        }

        public Builder addAppAssociation(Bundle appAssociation) {
            Bundle[] appList = new Bundle[this.appList.length + 1];
            System.arraycopy(this.appList, 0, appList, 0, appList.length - 1);
            appList[this.appList.length] = appAssociation;
            this.appList = appList;
            return this;
        }

        public Builder addAppAssociation(String packageName, String[] activities) {
            Bundle[] appAssociationsList = new Bundle[appList.length + 1];
            System.arraycopy(appList, 0, appAssociationsList, 0,
                    appAssociationsList.length - 1);
            Bundle newAppAssociation = new Bundle();
            newAppAssociation.putString(PACKAGE_NAME_KEY, packageName);
            newAppAssociation.putStringArray(ACTIVITY_LIST_KEY, activities);
            appAssociationsList[appAssociationsList.length -1] = newAppAssociation;
            this.appList = appAssociationsList;
            return this;
        }

        public Bundle create() {
            return new MainBundle(this).getPlugin();
        }
    }
}
