package com.bzinoun.premierleaguenews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bzinoun.premierleaguenews.model.data.RealmManager;

/**
 * Created by hungvu on 8/27/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmManager.initializeRealmConfig(this);
    }
}
