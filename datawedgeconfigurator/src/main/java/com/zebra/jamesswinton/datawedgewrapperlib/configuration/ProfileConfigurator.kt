package com.zebra.jamesswinton.datawedgewrapperlib.configuration

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.ConfigMode
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants

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
        internal var configMode = ConfigMode.CREATE_IF_NOT_EXIST
        internal var profileEnabled = true
        internal var pluginBundles = ArrayList<Bundle>()
        internal var appList = arrayOfNulls<Bundle>(0)
        internal var dcpPlugin: Bundle? = null

        fun setProfileName(profileName: String): Builder {
            this.profileName = profileName
            return this
        }

        fun setConfigMode(configMode: ConfigMode): Builder {
            this.configMode = configMode
            return this
        }

        fun setProfileEnabled(profileEnabled: Boolean): Builder {
            this.profileEnabled = profileEnabled
            return this
        }

        fun addPlugin(plugin: Bundle): Builder {
            pluginBundles.add(plugin)
            return this
        }

        fun addArrayOfPlugins(pluginBundles: ArrayList<Bundle>): Builder {
            this.pluginBundles = pluginBundles
            return this
        }

        fun addAppAssociation(packageName: String, activityPath: String): Builder {
            val newAppAssociation = Bundle().apply {
                putString(Constants.PACKAGE_NAME_KEY, packageName)
                putStringArray(Constants.ACTIVITY_LIST_KEY, arrayOf(activityPath))
            }

            val appAssociationsList = appList.copyOf(appList.size + 1)
            appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
            this.appList = appAssociationsList

            return this
        }

        fun addAppAssociation(packageName: String, activitiesPaths: Array<String>): Builder {
            val newAppAssociation = Bundle().apply {
                putString(Constants.PACKAGE_NAME_KEY, packageName)
                putStringArray(Constants.ACTIVITY_LIST_KEY, activitiesPaths)
            }

            val appAssociationsList = appList.copyOf(appList.size + 1)
            appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
            this.appList = appAssociationsList

            return this
        }

        fun addAppAssociation(packageName: String): Builder {
            val newAppAssociation = Bundle().apply {
                putString(Constants.PACKAGE_NAME_KEY, packageName)
                putStringArray(Constants.ACTIVITY_LIST_KEY, arrayOf("*"))
            }

            val appAssociationsList = appList.copyOf(appList.size + 1)
            appAssociationsList[appAssociationsList.size - 1] = newAppAssociation
            this.appList = appAssociationsList

            return this
        }

        fun setDataCapturePlus(dcpPlugin: Bundle): Builder {
            this.dcpPlugin = dcpPlugin
            return this
        }

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
        private const val APP_LIST_KEY = "APP_LIST"
        private const val DCP_SUPPORT = "DCP"
    }
}