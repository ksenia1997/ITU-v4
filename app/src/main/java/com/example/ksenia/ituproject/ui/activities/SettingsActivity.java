package com.example.ksenia.ituproject.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Currency;
import com.example.ksenia.ituproject.model.Status;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity {
    // boolean wasDarkMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializace okna.
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        // Zapinani dark mode.
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

        // Tlacitko pro vyber hlavni meny.
        Button currency_button = (Button) findViewById(R.id.currency_button);

        // Nacitani moznosti pro vyber hlavni meny.
        final String[] currencies = new String[Status.getCurrencies().size()];
        int idx = 0;
        for(Currency c : Status.getCurrencies())
        {
            currencies[idx] = c.getName();
            idx++;
        }

        // Akce po stisku tlacitka.
        currency_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialogove okno.
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
                // Nastavovani titulku.
                mBuilder.setTitle("Choose main currency");
                // Reakce na novou hodnotu dialogu.
                mBuilder.setSingleChoiceItems(currencies, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Doplnit reakci na hodnotu.
                        MyApp.status.setMainCurrency(Status.getCurrencies().get(i));
                        dialogInterface.dismiss();
                    }
                });
                // Zobrazeni dialogoveho okna.
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        // Tlacitko pro nastavovani kurzu meny.
        Button rate_button = (Button) findViewById(R.id.rate_button);

        // Akce po stisku tlacitka.
        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialogove okno.
                startActivity(new Intent(SettingsActivity.this, CurrencyActivity.class));
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

    public void setCureencies() {

    }
}
