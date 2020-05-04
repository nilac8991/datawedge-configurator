package com.jamesswinton.datawedgewrapper;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zebra.jamesswinton.datawedgewrapperlib.DataWedgeWrapper;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.AppAssociations;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.MainBundle;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.BDFPlugin;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.EKBPlugin;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.KeystrokePlugin;
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnIntentResultListener;
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnLastResultIntentListener;
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    // Debugging
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Test Set Config
        // testSetConfig();

    }

    private void testSetConfig() {
        // Create Plugin Bundles
        Bundle ekbPlugin = new EKBPlugin.Builder()
                .setEnabled(true)
                .create();

        Bundle keystrokePlugin = new KeystrokePlugin.Builder()
                .setEnabled(true)
                .create();

        Bundle bdfPlugin = new BDFPlugin.Builder()
                .setEnabled(true)
                .setOutputPluginName(Constants.OutputPluginName.KEYSTROKE)
                .sendEnter(true)
                .create();

        // Create App Associations
        Bundle[] appAssociations = new AppAssociations.Builder()
                .addAppAssociation(getPackageName(), new String[]{("*")})
                .create();

        // Create Main Bundle
        Bundle mainBundle = new MainBundle.Builder()
                .setProfileName("Profile0 (default)")
                .setProfileEnabled(true)
                .addPluginBundle(bdfPlugin)
                .addPluginBundle(keystrokePlugin)
                .create();

        // Send Intent With Result
        DataWedgeWrapper.sendIntentWithLastResult(this, Constants.IntentType.SET_CONFIG,
                mainBundle, "Test", new OnLastResultIntentListener() {
                    @Override
                    public void onResult(boolean wasSuccessful,
                                         @Nullable Bundle resultInfo,
                                         @Nullable String resultString,
                                         @Nullable String command, @Nullable String profileName) {
                        Log.i(TAG, "Last Result Success: " + wasSuccessful);
                    }
                });
    }
}
