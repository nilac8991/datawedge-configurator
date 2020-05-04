package com.zebra.jamesswinton.datawedgewrapperlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnCompleteResultIntentListener;
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnIntentResultListener;
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnLastResultIntentListener;
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants.*;

import java.util.ArrayList;
import java.util.Set;

public class DataWedgeWrapper {

    // Debugging
    private static final String TAG = "DataWedgeWrapper";

    // Intent Placeholders
    private static final String DW_ACTION_STRING = "com.symbol.datawedge.api.ACTION";
    private static final String INTENT_TYPE_KEY_PREFIX = "com.symbol.datawedge.api.";

    private static final String SEND_RESULT_KEY = "SEND_RESULT";
    private static final String LAST_RESULT = "LAST_RESULT";
    private static final String COMPLETE_RESULT = "COMPLETE_RESULT";
    private static final String COMMAND_IDENTIFIER_KEY = "COMMAND_IDENTIFIER";

    // Result Placeholders
    private static final String INTENT_ACTION = "com.symbol.datawedge.api.RESULT_ACTION";
    private static final String INTENT_CATEGORY = "android.intent.category.DEFAULT";

    // Interface
    private static OnLastResultIntentListener mOnLastResultIntentListener = null;
    private static OnCompleteResultIntentListener mOnCompleteResultIntentListener = null;

    // Intent Methods
    public static void sendIntent(Context context, IntentType intentType, Bundle data) {
        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);

        // Send Intent
        context.sendBroadcast(i);
    }

    public static void sendIntent(Context context, IntentType intentType, String data) {
        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);

        // Send Intent
        context.sendBroadcast(i);
    }

    public static void sendIntentWithLastResult(Context context, IntentType intentType, Bundle data,
                                                String identifier,
                                                OnLastResultIntentListener onLastResultIntentListener) {
        // Store Interface
        mOnLastResultIntentListener = onLastResultIntentListener;

        // Register Result Receiver
        registerReceiver(context);

        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);
        i.putExtra(SEND_RESULT_KEY, LAST_RESULT);
        i.putExtra(COMMAND_IDENTIFIER_KEY, identifier);

        // Send Intent
        context.sendBroadcast(i);
    }

    public static void sendIntentWithLastResult(Context context, IntentType intentType, String data,
                                                String identifier,
                                                OnLastResultIntentListener onLastResultIntentListener) {
        // Store Interface
        mOnLastResultIntentListener = onLastResultIntentListener;

        // Register Result Receiver
        registerReceiver(context);

        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);
        i.putExtra(SEND_RESULT_KEY, LAST_RESULT);
        i.putExtra(COMMAND_IDENTIFIER_KEY, identifier);

        // Send Intent
        context.sendBroadcast(i);
    }

    public static void sendIntentWithCompleteResult(Context context, IntentType intentType, Bundle data,
                                                    String identifier,
                                                    OnCompleteResultIntentListener onCompleteResultIntentListener) {
        // Store Interface
        mOnCompleteResultIntentListener = onCompleteResultIntentListener;

        // Register Result Receiver
        registerReceiver(context);

        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);
        i.putExtra(SEND_RESULT_KEY, LAST_RESULT);
        i.putExtra(COMMAND_IDENTIFIER_KEY, identifier);

        // Send Intent
        context.sendBroadcast(i);
    }

    public static void sendIntentWithCompleteResult(Context context, IntentType intentType, String data,
                                                    String identifier,
                                                    OnCompleteResultIntentListener onCompleteResultIntentListener) {
        // Store Interface
        mOnCompleteResultIntentListener = onCompleteResultIntentListener;

        // Register Result Receiver
        registerReceiver(context);

        // Build Intent
        Intent i = new Intent();
        i.setAction(DW_ACTION_STRING);
        i.putExtra(INTENT_TYPE_KEY_PREFIX + intentType.name(), data);
        i.putExtra(SEND_RESULT_KEY, LAST_RESULT);
        i.putExtra(COMMAND_IDENTIFIER_KEY, identifier);

        // Send Intent
        context.sendBroadcast(i);
    }

    /**
     * Broadcast Receiver
     */

    private static final BroadcastReceiver dwIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Unregister Receiver
            unregisterReceiver(context);

            // Get Results
            String action = intent.getAction();
            String command = intent.getStringExtra("COMMAND");
            String profileName = intent.getStringExtra("PROFILE_NAME");
            if (action != null && action.equals(INTENT_ACTION)) {
                // Get COMPLETE_RESULT
                if (intent.hasExtra("RESULT_LIST")) {
                    // Get Result List
                    ArrayList<Bundle> resultList = (ArrayList) intent
                            .getSerializableExtra("RESULT_LIST");

                    String resultInfo = "";
                    for (Bundle bundleResult : resultList) {
                        resultInfo += "\n\n";
                        Set<String> keys = bundleResult.keySet();
                        for (String key : keys) {
                            String val = bundleResult.getString(key);
                            if (val == null) {
                                if (bundleResult.getStringArray(key) != null) {
                                    val = "";
                                    String[] resultInfoStringArray = bundleResult.getStringArray(key);
                                    if (resultInfoStringArray != null) {
                                        for (String s : resultInfoStringArray) {
                                            val += "" + s + "\n";
                                        }
                                    }
                                }
                            }

                            resultInfo += key + ": " + val + "\n";
                        }
                    }

                    // Pass Results Up
                    mOnCompleteResultIntentListener.onResult(resultList, resultInfo, command,
                            profileName);

                } else if (intent.hasExtra("RESULT_INFO")) {
                    // Get Result
                    String result = intent.getStringExtra("RESULT");
                    boolean successful = result != null && result.equals("SUCCESS");
                    // Get Result Info
                    Bundle resultInfo = intent.getBundleExtra("RESULT_INFO");

                    String resultInfoString = "";
                    resultInfoString += "Result: " + result + "\n";
                    Set<String> keys = null;
                    if (resultInfo != null) {
                        keys = resultInfo.keySet();
                        for (String key : keys) {
                            String val = resultInfo.getString(key);
                            if (val == null) {
                                if (resultInfo.getStringArray(key) != null) {
                                    val = "";
                                    String[] resultInfoStringArray = resultInfo.getStringArray(key);
                                    if (resultInfoStringArray != null) {
                                        for (String s : resultInfoStringArray) {
                                            val += "" + s + "\n";
                                        }
                                    }
                                }
                            }

                            resultInfoString += key + ": " + val + "\n";
                        }
                    }

                    // Pass Results Up
                    mOnLastResultIntentListener.onResult(successful, resultInfo,
                            resultInfoString, command, profileName);
                }
            } else {
                Log.w(TAG, "Ignoring received Intent that didn't have action: " + INTENT_ACTION);
            }
        }
    };

    private static void registerReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(INTENT_ACTION);
        filter.addCategory(INTENT_CATEGORY);
        context.registerReceiver(dwIntentReceiver, filter);
    }

    private static void unregisterReceiver(Context context) {
        context.unregisterReceiver(dwIntentReceiver);
    }

}
