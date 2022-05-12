package com.zebra.jamesswinton.datawedgewrapperlib.interfaces;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface OnIntentResultListener {
    void onCompleteResult(@Nullable ArrayList<Bundle> resultInfo, @Nullable String resultString,
                          String command, String profileName);
    void onLastResult(boolean wasSuccessful, @Nullable Bundle resultInfo,
                      @Nullable String resultString, String command, String profileName);
}
