package com.bzinoun.premierleaguenews.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.fixture.Fixture;
import com.bzinoun.premierleaguenews.utils.Utils;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungvu on 10/20/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.adapter
 */

public class LastestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //flag = 0 : next
    //flag = 1 : last
    OnClickLastest onClickLastest;
    private Utils utils = Utils.getInstance();
    private Context context;
    private List<Fixture> fixtureList = new ArrayList<>();
    private List<TeamDataBean> teamDataList = new ArrayList<>();
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;



    public LastestAdapter(Context context, List<Fixture> fixtures, List<TeamDataBean> teamDataList) {
        this.context = context;
        this.fixtureList = fixtures;
        this.teamDataList = teamDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lastest, parent, false);
        return new LastestHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final LastestHolder nextLastHolder = (LastestHolder) holder;
        final Fixture currentFixture = fixtureList.get(position);
        nextLastHolder.tvTeam1.setText(utils.getShortTeamName(currentFixture.getHomeTeamName(), teamDataList));


        Utils.BindImageUrlToView(requestBuilder , context , utils.getLogoByName(currentFixture.getAwayTeamName() ,teamDataList), nextLastHolder.imgTeam2);
        Utils.BindImageUrlToView(requestBuilder , context , utils.getLogoByName(currentFixture.getHomeTeamName() ,teamDataList), nextLastHolder.imgTeam1);


        nextLastHolder.tvTeam2.setText(utils.getShortTeamName(currentFixture.getAwayTeamName(), teamDataList));
        nextLastHolder.tvTime.setText(currentFixture.getResult().getGoalsHomeTeam() + "-" + currentFixture.getResult().getGoalsAwayTeam());
        nextLastHolder.layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLastest.clickLastest(currentFixture.getLinks().getSelf().getHref());
            }
        });

    }

    @Override
    public int getItemCount() {
        return fixtureList == null ? 0 : fixtureList.size();
    }

    public void setOnClickLastest(OnClickLastest onClickLastest) {
        this.onClickLastest = onClickLastest;
    }

    public interface OnClickLastest {
        void clickLastest(String linkFixture);
    }

    class LastestHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layoutRoot)
        LinearLayout layoutRoot;
        @BindView(R.id.imgTeam1)
        ImageView imgTeam1;
        @BindView(R.id.imgTeam2)
        ImageView imgTeam2;
        @BindView(R.id.tvTeam1)
        TextView tvTeam1;
        @BindView(R.id.tvTeam2)
        TextView tvTeam2;
        @BindView(R.id.tvTime)
        TextView tvTime;

        public LastestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
