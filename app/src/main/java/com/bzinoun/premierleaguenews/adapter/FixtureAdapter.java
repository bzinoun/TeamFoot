package com.bzinoun.premierleaguenews.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.fixture.Fixture;
import com.bzinoun.premierleaguenews.utils.Utils;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungvu on 10/17/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.adapter
 */

public class FixtureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Utils utils;
    private Context context;
    private List<Fixture> fixtureList = new ArrayList<>();
    private List<TeamDataBean> teamDataList = new ArrayList<>();
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;


    public FixtureAdapter(Context context, List<Fixture> fixtures, List<TeamDataBean> teamDataList) {
        this.context = context;
        this.fixtureList = fixtures;
        this.teamDataList = teamDataList;
        utils = Utils.getInstance();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_fixtures, parent, false);
        return new FixtureHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Fixture currentFixture = fixtureList.get(position);
        FixtureHolder fixtureHolder = (FixtureHolder) holder;
       // int logo1 = utils.getLogoByName(currentFixture.getHomeTeamName(), teamDataList);
        //int logo2 = utils.getLogoByName(currentFixture.getAwayTeamName(), teamDataList);

        Utils.BindImageUrlToView(requestBuilder , context , utils.getLogoByName(currentFixture.getHomeTeamName() ,teamDataList), fixtureHolder.imgLogo1);

        Utils.BindImageUrlToView( requestBuilder ,context ,  utils.getLogoByName(currentFixture.getAwayTeamName(), teamDataList) , fixtureHolder.imgLogo2);

        fixtureHolder.tvTeam1.setText(utils.getShortTeamName(currentFixture.getHomeTeamName(), teamDataList));
        fixtureHolder.tvTeam2.setText(utils.getShortTeamName(currentFixture.getAwayTeamName(), teamDataList));
        fixtureHolder.tvTime.setText(converToHour(currentFixture.getDate()));
        fixtureHolder.tvDate.setText(currentFixture.getDate());

    }




    public String converToHour(String time) {
        String result = null;
        SimpleDateFormat currentSimple = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'");
        SimpleDateFormat formatter_to = new SimpleDateFormat("hh:mm");
        try {
            Date date = currentSimple.parse(time);
            result = formatter_to.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String convertToDate(String time) {
        String result = null;
        SimpleDateFormat currentSimple = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'");
        SimpleDateFormat formatter_to = new SimpleDateFormat("yyyy-mm-dd'T'");
        try {
            Date date = currentSimple.parse(time);
            result = formatter_to.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int getItemCount() {
        return fixtureList.size();
    }

    class FixtureHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNameTeam1)
        TextView tvTeam1;
        @BindView(R.id.tvNameTeam2)
        TextView tvTeam2;
        @BindView(R.id.imgLogo1)
        ImageView imgLogo1;
        @BindView(R.id.imgLogo2)
        ImageView imgLogo2;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvDate)
        TextView tvDate;

        public FixtureHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
