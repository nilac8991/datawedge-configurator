package com.zebra.jamesswinton.datawedgewrapperlib.configuration

import android.content.Context
import com.zebra.jamesswinton.datawedgewrapperlib.DataWedgeWrapper
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnLastResultIntentListener
import com.zebra.jamesswinton.datawedgewrapperlib.models.CommandIdentifier
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants.IntentType

object ScannerConfigurator {

    private const val TAG = "ScannerConfigurator"

    private enum class ScannerStatus {
        RESUME_PLUGIN, SUSPEND_PLUGIN, ENABLE_PLUGIN, DISABLE_PLUGIN
    }

    fun resumeScanner(context: Context) {
        DataWedgeWrapper.sendIntent(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.RESUME_PLUGIN.name
        )
    }

    fun resumeScannerWithResult(
        context: Context,
        onLastResultIntentListener: OnLastResultIntentListener
    ) {
        DataWedgeWrapper.sendIntentWithLastResult(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.RESUME_PLUGIN.name,
            CommandIdentifier.MY_RESUME_SCANNER,
            onLastResultIntentListener
        )
    }

    fun suspendScanner(context: Context) {
        DataWedgeWrapper.sendIntent(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.SUSPEND_PLUGIN.name
        )
    }

    fun suspendScannerWithResult(
        context: Context,
        onLastResultIntentListener: OnLastResultIntentListener
    ) {
        DataWedgeWrapper.sendIntentWithLastResult(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.SUSPEND_PLUGIN.name,
            CommandIdentifier.MY_SUSPEND_SCANNER,
            onLastResultIntentListener
        )
    }

    fun enableScanner(context: Context) {
        DataWedgeWrapper.sendIntent(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.ENABLE_PLUGIN.name
        )
    }

    fun enableScannerWithResult(
        context: Context,
        onLastResultIntentListener: OnLastResultIntentListener
    ) {
        DataWedgeWrapper.sendIntentWithLastResult(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.ENABLE_PLUGIN.name,
            CommandIdentifier.MY_ENABLE_SCANNER,
            onLastResultIntentListener
        )
    }

    fun disableScanner(context: Context) {
        DataWedgeWrapper.sendIntent(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.DISABLE_PLUGIN.name
        )
    }

    fun disableScannerWithResult(
        context: Context,
        onLastResultIntentListener: OnLastResultIntentListener
    ) {
        DataWedgeWrapper.sendIntentWithLastResult(
            context,
            IntentType.SCANNER_INPUT_PLUGIN,
            ScannerStatus.DISABLE_PLUGIN.name,
            CommandIdentifier.MY_DISABLE_SCANNER,
            onLastResultIntentListener
        )
    }
}