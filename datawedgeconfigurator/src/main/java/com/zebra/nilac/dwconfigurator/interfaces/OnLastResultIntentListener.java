package com.zebra.nilac.dwconfigurator.interfaces;

import android.os.Bundle;

import androidx.annotation.Nullable;

public interface OnLastResultIntentListener {
    void onResult(boolean wasSuccessful, @Nullable Bundle resultInfo,
                  @Nullable String resultString, @Nullable String command,
                  @Nullable String profileName);
}
