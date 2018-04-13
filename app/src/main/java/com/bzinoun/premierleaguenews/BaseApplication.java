package com.bzinoun.premierleaguenews;

import android.app.Application;

import com.bzinoun.premierleaguenews.utils.FontsOverride;

import io.realm.Realm;

/**
 * Created by hungvu on 8/28/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "DEFAULT", "SFUShannonBook.TTF");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "SFUShannonBook.TTF");
        FontsOverride.setDefaultFont(this, "SERIF", "SFUShannonBook.TTF");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "SFUShannonBook.TTF");
        Realm.init(this);

    }
}
