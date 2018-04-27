package com.bzinoun.premierleaguenews.model.data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Zhuinden on 2016.08.16..
 */
public class RealmManager {
    static Realm realm;

    static RealmConfiguration realmConfiguration;
    private static int activityCount = 0;

    public static void initializeRealmConfig(Context appContext) {
        if (realmConfiguration == null) {
            Realm.init(appContext);
            RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

            setRealmConfiguration(config);
        }
    }

    public static void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        RealmManager.realmConfiguration = realmConfiguration;
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm getRealm() {
        return realm;
    }

    public static void incrementCount() {
        if (activityCount == 0) {
            if (realm != null) {
                if (!realm.isClosed()) {
                    realm.close();
                }
            }
            realm = Realm.getDefaultInstance();
        }
        activityCount++;
    }

    public static void decrementCount() {
        activityCount--;
        if (activityCount <= 0) {
            activityCount = 0;
            realm.close();
            Realm.compactRealm(realmConfiguration);
            realm = null;
        }
    }
}