package com.zebra.jamesswinton.datawedgewrapperlib.interfaces;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface OnLastResultIntentListener {
    void onResult(boolean wasSuccessful, @Nullable Bundle resultInfo,
                  @Nullable String resultString, @Nullable String command,
                  @Nullable String profileName);
}
