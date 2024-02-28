package com.zebra.nilac.dwconfigurator.configuration.plugins

import android.os.Bundle
import com.zebra.nilac.dwconfigurator.models.dcp.DCPButtonAnchorPosition
import com.zebra.nilac.dwconfigurator.models.dcp.DCPLaunchMode

class DCPPlugin private constructor(builder: Builder) : GenericPlugin() {

    init {
        paramList.apply {
            putString(DCP_BUTTON_ANCHOR_POSITION, builder.buttonAnchorPosition.name)
            putString(DCP_LAUNCH_MODE, builder.launchMode.name)
            putString(DCP_HIGHEST_POSITION, builder.highestPositionValue.toString())
            putString(DCP_LOWEST_POSITION, builder.lowestPositionValue.toString())
            putString(DCP_TOUCH_WAIT_TIME, builder.touchWaitTime.toString())

            putString(DCP_ENABLED_KEY, if (builder.pluginEnabled) "true" else "false")
            putString(RESET_CONFIG_KEY, if (builder.resetConfig) "true" else "false")
        }
    }

    class Builder {
        // Config
        var resetConfig = true

        //Params
        internal var pluginEnabled: Boolean = false
        internal var buttonAnchorPosition: DCPButtonAnchorPosition = DCPButtonAnchorPosition.BOTH
        internal var launchMode: DCPLaunchMode = DCPLaunchMode.BUTTON
        internal var highestPositionValue = 100
        internal var lowestPositionValue = 100
        internal var touchWaitTime = 100

        fun resetConfig(resetConfig: Boolean): Builder =
            apply { this.resetConfig = resetConfig }

        fun setEnabled(enabled: Boolean): Builder =
            apply { this.pluginEnabled = enabled }

        fun setButtonAnchorPosition(buttonAnchorPosition: DCPButtonAnchorPosition): Builder =
            apply { this.buttonAnchorPosition = buttonAnchorPosition }

        fun setLaunchMode(launchMode: DCPLaunchMode): Builder =
            apply { this.launchMode = launchMode }

        fun setHighestPositionValue(value: Int): Builder =
            apply {
                this.highestPositionValue = if (value > 100) {
                    100
                } else {
                    value
                }
            }

        fun setLowestPositionValue(value: Int): Builder =
            apply {
                this.lowestPositionValue = if (value > 100) {
                    100
                } else {
                    value
                }
            }

        fun setTouchWaitTime(value: Int): Builder =
            apply { this.touchWaitTime = value }

        fun create(): Bundle {
            return DCPPlugin(this).plugin
        }
    }

    companion object {

        private const val DCP_ENABLED_KEY = "dcp_input_enabled"
        private const val DCP_BUTTON_ANCHOR_POSITION = "dcp_dock_button_on"
        private const val DCP_LAUNCH_MODE = "dcp_start_in"
        private const val DCP_HIGHEST_POSITION = "dcp_highest_pos"
        private const val DCP_LOWEST_POSITION = "dcp_lowest_pos"
        private const val DCP_TOUCH_WAIT_TIME = "dcp_drag_detect_time"
    }
}