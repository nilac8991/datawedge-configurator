package com.zebra.nilac.dwconfigurator.configuration

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.ConfigMode

class ProfileConfigurator private constructor(builder: Builder) {

    // Plugin
    private val plugin = Bundle()

    init {
        plugin.putString(PROFILE_NAME_KEY, builder.profileName)
        plugin.putString(CONFIG_MODE_KEY, builder.configMode.name)
        plugin.putString(PROFILE_ENABLED_KEY, if (builder.profileEnabled) "true" else "false")
        plugin.putParcelableArrayList(PLUGIN_CONFIG_KEY, builder.pluginBundles)

        if (builder.appList.isNotEmpty()) {
            plugin.putParcelableArray(APP_LIST_KEY, builder.appList)
        }

        if (builder.dcpPlugin != null) {
            plugin.putBundle(DCP_SUPPORT, builder.dcpPlugin)
        }
    }

    class Builder {
        internal var profileName = ""
        internal var profileEnabled = true

        internal var configMode = ConfigMode.CREATE_IF_NOT_EXIST
        internal var pluginBundles = ArrayList<Bundle>()
        internal var appList = arrayOfNulls<Bundle>(0)
        internal var dcpPlugin: Bundle? = null

        fun setProfileName(profileName: String): Builder =
            apply { this.profileName = profileName }

        fun setConfigMode(configMode: ConfigMode): Builder =
            apply { this.configMode = configMode }

        fun setProfileEnabled(profileEnabled: Boolean): Builder =
            apply { this.profileEnabled = profileEnabled }

        fun addPlugin(plugin: Bundle): Builder =
            apply { pluginBundles.add(plugin) }

        fun addArrayOfPlugins(pluginBundles: ArrayList<Bundle>): Builder =
            apply { this.pluginBundles = pluginBundles }

        fun addAppAssociation(packageName: String, activityPath: String): Builder =
            apply {
                val newAppAssociation = Bundle().apply {
                    putString(PACKAGE_NAME_KEY, packageName)
                    putStringArray(ACTIVITY_LIST_KEY, arrayOf(activityPath))
                }

                val appAssociationsList = appList.copyOf(appList.size + 1)
                appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
                this.appList = appAssociationsList
            }

        fun addAppAssociation(packageName: String, activitiesPaths: Array<String>): Builder =
            apply {
                val newAppAssociation = Bundle().apply {
                    putString(PACKAGE_NAME_KEY, packageName)
                    putStringArray(ACTIVITY_LIST_KEY, activitiesPaths)
                }

                val appAssociationsList = appList.copyOf(appList.size + 1)
                appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
                this.appList = appAssociationsList
            }

        fun addAppAssociation(packageName: String): Builder =
            apply {
                val newAppAssociation = Bundle().apply {
                    putString(PACKAGE_NAME_KEY, packageName)
                    putStringArray(ACTIVITY_LIST_KEY, arrayOf("*"))
                }

                val appAssociationsList = appList.copyOf(appList.size + 1)
                appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
                this.appList = appAssociationsList
            }

        fun setDataCapturePlus(dcpPlugin: Bundle): Builder =
            apply { this.dcpPlugin = dcpPlugin }

        fun create(): Bundle {
            return ProfileConfigurator(this).plugin
        }
    }

    companion object {
        // Plugin Parameter Names
        private const val PROFILE_NAME_KEY = "PROFILE_NAME"
        private const val CONFIG_MODE_KEY = "CONFIG_MODE"
        private const val PROFILE_ENABLED_KEY = "PROFILE_ENABLED"
        private const val PLUGIN_CONFIG_KEY = "PLUGIN_CONFIG"

        //App Associations
        private const val APP_LIST_KEY = "APP_LIST"
        private const val PACKAGE_NAME_KEY = "PACKAGE_NAME"
        private const val ACTIVITY_LIST_KEY = "ACTIVITY_LIST"

        //Data Capture Plus
        private const val DCP_SUPPORT = "DCP"
    }
}