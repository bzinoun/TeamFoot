package com.bzinoun.premierleaguenews.fragment.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.adapter.FixtureAdapter;
import com.bzinoun.premierleaguenews.model.data.RealmManager;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.data.TeamDataDao;
import com.bzinoun.premierleaguenews.model.fixture.Fixture;
import com.bzinoun.premierleaguenews.model.fixture.FixtureAPIBean;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.service.TeamDataService;
import com.bzinoun.premierleaguenews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hungvu on 8/29/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.fragment
 */

public class TeamFixtureFragment extends Fragment {
    private static TeamFixtureFragment teamFixtureFragment = null;
    @BindView(R.id.listFixture)
    RecyclerView listFixtures;
    private Utils utils = Utils.getInstance();
    FBAPIService mService;
    private TeamDataService teamDataService = TeamDataService.getInstance();
    private List<TeamDataBean> teamDataList = new ArrayList<>();
    private TeamDataDao teamDataDao;
    public static TeamFixtureFragment getInstance(int link) {
        if (teamFixtureFragment == null) {
            teamFixtureFragment = new TeamFixtureFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("link", link);
        teamFixtureFragment.setArguments(bundle);
        return teamFixtureFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        RealmManager.incrementCount();
        teamDataDao = new TeamDataDao(RealmManager.getRealm());

        ButterKnife.bind(this, view);
        int teamID = getArguments().getInt("link");
        mService = utils.getFABAPIService();
        mService.getNextLastFixture(getString(R.string.token), teamID, "n30").enqueue(new Callback<FixtureAPIBean>() {
            @Override
            public void onResponse(Call<FixtureAPIBean> call, Response<FixtureAPIBean> response) {
                initFixture(response.body().getFixtures());
            }

            @Override
            public void onFailure(Call<FixtureAPIBean> call, Throwable t) {

            }
        });
        return view;
    }

    public void initFixture(List<Fixture> listFixture) {
        List<TeamDataBean> teamDataList = teamDataDao.findAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listFixtures.setLayoutManager(layoutManager);
        FixtureAdapter fixtureAdapter = new FixtureAdapter(getContext(), listFixture, teamDataList);
        listFixtures.setAdapter(fixtureAdapter);
    }

    @Override
    public void onDestroy() {
        RealmManager.decrementCount();
        super.onDestroy();
    }
}
