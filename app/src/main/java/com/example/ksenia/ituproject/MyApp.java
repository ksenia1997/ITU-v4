package com.example.ksenia.ituproject;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Status;
import com.example.ksenia.ituproject.ui.activities.MainActivity;

import java.util.ArrayList;

public class MyApp extends Application {

    public static Status status;

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        initCategories();
        // Required initialization logic here!
    }

    private void initCategories() {
        ArrayList<Category> data = new ArrayList<>();
        data.add(new Category("Food", ContextCompat.getColor(getApplicationContext(), R.color.colorDefaultFood)));
        data.add(new Category("Smoking", ContextCompat.getColor(getApplicationContext(), R.color.colorDefaultSmoking)));
        data.add(new Category("Health", ContextCompat.getColor(getApplicationContext(), R.color.colorDefaultHealth)));
        data.add(new Category("Entertainment", ContextCompat.getColor(getApplicationContext(), R.color.colorDefaultEntertainment)));
        MyApp.status.saveCategories(data);
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
