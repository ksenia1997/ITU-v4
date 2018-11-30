package com.example.ksenia.ituproject.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Status;
import com.example.ksenia.ituproject.ui.fragments.CategoriesFragment;
import com.example.ksenia.ituproject.ui.fragments.CategoriesFragment.Listener;
import com.example.ksenia.ituproject.ui.fragments.Statistics;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements Listener {

    private FloatingActionButton fabAddTransaction;
    boolean wasDarkMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init layout
        setContentView(R.layout.activity_main);
        // set up click listeners
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }



    //region Listeners

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                openSettings();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment  = null;
            switch(item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_statistics:
                    selectedFragment = new Statistics();
                    break;
                case R.id.navigation_categories:
                    selectedFragment = new CategoriesFragment();
                    break;
                    //selectedFragment = new CategoriesFragment();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
            return true;
        }
    };

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, 100);
        wasDarkMode = getDarkMode();
    }

    @Override
    public void onFragmentInteraction() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
           boolean isDarkMode = getDarkMode();
           if (isDarkMode != wasDarkMode) {
               restart();
           }
           return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    //endregion
    public void restart() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


}
