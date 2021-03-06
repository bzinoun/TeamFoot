package com.bzinoun.premierleaguenews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bzinoun.premierleaguenews.fragment.ChartFragment;
import com.bzinoun.premierleaguenews.fragment.FixtureFragment;
import com.bzinoun.premierleaguenews.fragment.MoreFragment;
import com.bzinoun.premierleaguenews.fragment.NewFragment;

/**
 * Created by hungvu on 8/31/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.adapter
 */

public class PagerMainAdapter extends FragmentStatePagerAdapter {

    public PagerMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChartFragment();
        } else if (position == 1) {
            return new NewFragment();
        } else if (position == 2) {
            return new FixtureFragment();
        } else
            return new MoreFragment();

    }

    @Override
    public int getCount() {
        return 5;
    }
}
