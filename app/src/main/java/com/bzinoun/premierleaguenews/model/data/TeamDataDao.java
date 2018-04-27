package com.bzinoun.premierleaguenews.model.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TeamDataDao {

    private Realm mRealm;

    public TeamDataDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final TeamDataBean TeamData) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(TeamData);
            }
        });
    }

    public void save(final List<TeamDataBean> teamDataList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(teamDataList);
            }
        });
    }


    public List<TeamDataBean> findByCode(String teamCode) {

        List<TeamDataBean> teamDataList = new ArrayList<>();
        final RealmResults<TeamDataBean> teams = mRealm.where(TeamDataBean.class).equalTo("code", teamCode).findAll();

        for (TeamDataBean team : teams) {
            teamDataList.add(team);
        }


        return teamDataList;
    }

    public List<TeamDataBean> findAll() {

        List<TeamDataBean> teamDataList = new ArrayList<>();

        final RealmResults<TeamDataBean> teams = mRealm.where(TeamDataBean.class).findAll();
        for (TeamDataBean team : teams) {
            teamDataList.add(team);
        }


        return teamDataList;
    }


    public long count() {
        return mRealm.where(TeamDataBean.class).count();
    }

    public String getPath() {
        return mRealm.getPath();


    }


}