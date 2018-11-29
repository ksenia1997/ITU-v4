package com.example.ksenia.ituproject.ui.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ksenia.ituproject.ui.activities.StatisticsGraph1;
import com.example.ksenia.ituproject.ui.activities.StatisticsGraph2;

public class StatisticsAdapter extends FragmentPagerAdapter {


    public StatisticsAdapter(FragmentManager fm) {
        super(fm);
    }

    // return the fragment according to swiped positon
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatisticsGraph1();
            case 1:
                return new StatisticsGraph2();
        }
        return new StatisticsGraph1();
    }

    @Override
    public int getCount() {
        return 2;
    }
}