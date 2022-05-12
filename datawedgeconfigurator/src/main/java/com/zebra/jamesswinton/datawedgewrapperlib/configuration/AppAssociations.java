package com.zebra.jamesswinton.datawedgewrapperlib.configuration;

import android.os.Bundle;

public class AppAssociations {

    // Plugin Parameter Names
    private static final String PACKAGE_NAME_KEY = "PACKAGE_NAME";
    private static final String ACTIVITY_LIST_KEY = "ACTIVITY_LIST";

    // Plugin
    private Bundle[] plugin = new Bundle[0];

    private AppAssociations(Builder builder) {
        this.plugin = builder.appAssociations;
    }

    private Bundle[] getPlugin() {
        return plugin;
    }

    public static class Builder {

        private Bundle[] appAssociations = new Bundle[0];

        public Builder addAppAssociation(String packageName, String[] activities) {
            Bundle[] appAssociationsList = new Bundle[appAssociations.length + 1];
            System.arraycopy(appAssociations, 0, appAssociationsList, 0,
                    appAssociationsList.length - 1);
            Bundle newAppAssociation = new Bundle();
            newAppAssociation.putString(PACKAGE_NAME_KEY, packageName);
            newAppAssociation.putStringArray(ACTIVITY_LIST_KEY, activities);
            appAssociationsList[appAssociationsList.length -1] = newAppAssociation;
            this.appAssociations = appAssociationsList;
            return this;
        }

        public Bundle[] create() {
            return new AppAssociations(this).getPlugin();
        }

    }
}
