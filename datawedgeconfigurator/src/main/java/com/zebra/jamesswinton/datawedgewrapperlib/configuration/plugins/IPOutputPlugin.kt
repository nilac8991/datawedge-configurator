package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import android.util.Log
import com.zebra.jamesswinton.datawedgewrapperlib.models.ip.IPOutputProtocol

class IPOutputPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        val paramList = Bundle().apply {
            putString(IP_OUTPUT_ENABLE, if (builder.ipOutputEnabled) "true" else "false")
            putString(
                IP_OUTPUT_ENABLE_REMOTE_WEDGE,
                if (builder.ipOutputRemoteWedgeEnabled) "true" else "false"
            )
            putString(IP_OUTPUT_PROTOCOL, builder.ipOutputProtocol.name)
            putString(IP_OUTPUT_ADDRESS, builder.ipOutputAddress)
            putString(IP_OUTPUT_PORT, builder.ipOutputPort.toString())
        }

        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putBundle("PARAM_LIST", paramList)
    }

    class Builder {
        // Config
        var resetConfig = true

        //Params
        internal var ipOutputEnabled = false
        internal var ipOutputRemoteWedgeEnabled = false
        internal var ipOutputProtocol = IPOutputProtocol.TCP
        internal var ipOutputAddress = "127.0.0.1"
        internal var ipOutputPort = 80

        fun setEnabled(state: Boolean): Builder {
            this.ipOutputEnabled = state
            return this
        }

        fun setRemoteWedgeEnabled(state: Boolean): Builder {
            this.ipOutputRemoteWedgeEnabled = state
            return this
        }

        fun setProtocol(protocol: IPOutputProtocol): Builder {
            this.ipOutputProtocol = protocol
            return this
        }

        fun setAddress(address: String): Builder {
            this.ipOutputAddress = address
            return this
        }

        fun setPort(port: Int): Builder {
            this.ipOutputPort = if (port > 65535) {
                Log.w(TAG, "Warning invalid port number, setting to 65534")
                65534
            } else {
                port
            }
            return this
        }

        fun create(): Bundle {
            return IPOutputPlugin(this).plugin
        }
    }


    companion object {
        private const val PLUGIN_NAME = "IP"
        private const val TAG = "IPOutputPlugin"

        private const val IP_OUTPUT_ENABLE = "ip_output_enabled"
        private const val IP_OUTPUT_ENABLE_REMOTE_WEDGE = "ip_output_ip_wedge_enabled"
        private const val IP_OUTPUT_PROTOCOL = "ip_output_protocol"
        private const val IP_OUTPUT_ADDRESS = "ip_output_address"
        private const val IP_OUTPUT_PORT = "ip_output_port"
    }
}