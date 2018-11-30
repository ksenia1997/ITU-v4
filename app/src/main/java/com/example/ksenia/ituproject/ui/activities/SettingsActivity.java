package com.example.ksenia.ituproject.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.example.ksenia.ituproject.R;

public class SettingsActivity extends BaseActivity {
    // boolean wasDarkMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        Switch switch1 = (Switch)findViewById(R.id.switch1);
        switch1.setChecked(getDarkMode());
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefs = getSharedPreferences(SETTINGS_DEFAULT, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(SETTINGS_DARK_MODE, isChecked);
                editor.commit();

                setTheme();
                restart();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    public void restart() {
        finish();
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
