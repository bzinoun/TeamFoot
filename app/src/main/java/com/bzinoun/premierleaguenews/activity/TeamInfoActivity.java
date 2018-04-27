package com.bzinoun.premierleaguenews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.fragment.team.OverviewFragment;
import com.bzinoun.premierleaguenews.fragment.team.SquadFragment;
import com.bzinoun.premierleaguenews.fragment.team.TeamFixtureFragment;
import com.bzinoun.premierleaguenews.model.data.RealmManager;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.data.TeamDataDao;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class TeamInfoActivity extends AppCompatActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private String teamName;
    private Utils utils = Utils.getInstance();
    private Realm realm;
    private FBAPIService mService;
    private int logoSource;
    private String linkNews;
    private int teamId;
    private String fixtureTeam;
    private String playerTeam;
    private List<TeamDataBean> teamDataList = new ArrayList<>();

    //e66c32d50df447358ea63b34235dc8c3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        setContentView(R.layout.activity_team_info);
        ButterKnife.bind(this);

        RealmManager.incrementCount();
        final TeamDataDao teamDataDao = new TeamDataDao(RealmManager.getRealm());
        teamDataList = teamDataDao.findAll();

        teamName = getIntent().getExtras().getString("team_name");
        teamId = utils.getClubId(teamName, teamDataList);
        //fixtureTeam = "http://api.football-data.org/v1/teams/" + teamId + "/fixtures";
        //playerTeam = "http://api.football-data.org/v1/teams/" + teamId + "/players";
        tvTitle.setText(teamName + "");
        viewPager.setOffscreenPageLimit(2);
        TeamAdapter teamAdapter = new TeamAdapter(getSupportFragmentManager(), linkNews, teamId);
        viewPager.setAdapter(teamAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @OnClick(R.id.imgBack)
    public void onBack() {
        finish();
    }

    private class TeamAdapter extends FragmentStatePagerAdapter {
        private String linkNew;
        private int teamID;

        public TeamAdapter(FragmentManager fm, String linkNew, int teamID) {
            super(fm);
            this.linkNew = linkNew;
            this.teamID = teamID;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TeamFixtureFragment.getInstance(teamID);
                case 1:
                    return SquadFragment.getInstance(teamID);
                case 2:
                    return OverviewFragment.getInstance(linkNew);

                default:
                    return OverviewFragment.getInstance(linkNew);
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Upcoming Fixtures";
                case 1:
                    return "Squad";
                case 2:
                    return "Overview";
                default:
                    return "Overview";
            }
        }
    }

}
