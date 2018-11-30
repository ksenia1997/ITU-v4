package com.example.ksenia.ituproject.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

public class   BaseActivity extends AppCompatActivity {

    protected static final String SETTINGS_DEFAULT = "DEFAULT";

    protected static final String SETTINGS_DARK_MODE = "SETTINGS_DARK_MODE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
    }

    protected boolean getDarkMode() {
        SharedPreferences prefs = getSharedPreferences(SETTINGS_DEFAULT, Context.MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(SETTINGS_DARK_MODE, false);
        return isDarkMode;
    }

    protected void setTheme() {
        boolean isDarkMode = getDarkMode();
        AppCompatDelegate.setDefaultNightMode(isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

}
