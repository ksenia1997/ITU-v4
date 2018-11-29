package com.example.ksenia.ituproject.ui.listadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class StatisticsAdapter extends FragmentPagerAdapter {


    public StatisticsAdapter(FragmentManager fm) {
        super(fm);
    }

    // return the fragment according to swiped positon
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //return LoginFragment.newInstance();
            case 1:
                //return SignUpFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}