package com.example.ksenia.ituproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.ui.activities.CategoriesFragment.Listener;

public class MainActivity extends AppCompatActivity implements Listener {

    private FloatingActionButton fabAddTransaction;
    public static Status status = new Status();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init layout
        setContentView(R.layout.activity_main);
        // set up click listeners
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new WalletsFragment()).commit();



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
                    selectedFragment = new WalletsFragment();
                    break;
                case R.id.navigation_statistics:
                    selectedFragment = new Statistics();
                    break;
                case R.id.navigation_categories:
                    selectedFragment = new CategoriesFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
            return true;
        }
    };

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }



    @Override
    public void onFragmentInteraction() {

    }

    //endregion
}
