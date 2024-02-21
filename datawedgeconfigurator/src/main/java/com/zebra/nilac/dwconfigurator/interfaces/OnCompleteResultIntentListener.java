package com.zebra.nilac.dwconfigurator.interfaces;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface OnCompleteResultIntentListener {
    void onResult(@Nullable ArrayList<Bundle> resultInfo, @Nullable String resultString,
                  @Nullable String command, @Nullable String profileName);
}
