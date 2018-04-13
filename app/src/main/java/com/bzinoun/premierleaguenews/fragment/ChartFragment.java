package com.bzinoun.premierleaguenews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.activity.TeamInfoActivity;
import com.bzinoun.premierleaguenews.adapter.RankingAdapter;
import com.bzinoun.premierleaguenews.interfaces.OnClickRanking;
import com.bzinoun.premierleaguenews.model.premierleagueteam.PremierLeagueRank;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.utils.Utils;

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

public class ChartFragment extends Fragment implements OnClickRanking {

    @BindView(R.id.listRank)
    RecyclerView listRanking;
    private Utils utils = Utils.getInstance();
    private FBAPIService mService;
    private RankingAdapter rankingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);
        mService = utils.getFABAPIService();
        mService.getTeamRank(getString(R.string.token), Utils.getLeagueId()).enqueue(new Callback<PremierLeagueRank>() {
            @Override
            public void onResponse(Call<PremierLeagueRank> call, Response<PremierLeagueRank> response) {
                rankingAdapter = new RankingAdapter(getActivity(), response.body().getStanding());
                listRanking.setHasFixedSize(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                listRanking.setLayoutManager(layoutManager);
                listRanking.setAdapter(rankingAdapter);
                rankingAdapter.setOnClickRanking(ChartFragment.this);
            }

            @Override
            public void onFailure(Call<PremierLeagueRank> call, Throwable t) {
                Log.d("ChartFrag", t.toString() + "");
            }
        });

        return view;
    }

    @Override
    public void onClickTeam(String teamName) {
        Intent intent = new Intent(getActivity(), TeamInfoActivity.class);
        intent.putExtra("team_name", teamName);
        intent.putExtra("team_id", teamName);
        startActivity(intent);
    }
}
