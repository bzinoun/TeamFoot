package com.bzinoun.premierleaguenews.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.retrofit.RetrofitClient;
import com.bzinoun.premierleaguenews.svgloading.SvgDecoder;
import com.bzinoun.premierleaguenews.svgloading.SvgDrawableTranscoder;
import com.bzinoun.premierleaguenews.svgloading.SvgSoftwareLayerSetter;
import com.caverock.androidsvg.SVG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by hungvu on 8/27/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.utils
 */

public class Utils {
    private static final String BASE_URL = "http://api.football-data.org";
    private static Utils utils;
    private static String LEAGUE_ID = "450";

    public static Utils getInstance() {
        if (utils == null)
            utils = new Utils();
        return utils;
    }

    public static String getLeagueId() {
        return LEAGUE_ID;
    }

    public FBAPIService getFABAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(FBAPIService.class);
    }

    public String getTextFromAsset(String fileName, Context context) {
        String result = null;
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            result = total.toString();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return result;
    }

    public int getClubId(String clubName, List<TeamDataBean> teamDataList) {
        for (TeamDataBean teamAPIBean : teamDataList) {

            if (clubName.equalsIgnoreCase(teamAPIBean.getName())) {

                return Integer.valueOf(teamAPIBean.getId()).intValue();
            }
        }
        return 1;
    }

    public static  void BindImageUrlToView(GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder , Context context, String url, ImageView imgLogo1)
    {
        if (url.contains("svg")) {
            requestBuilder = Glide.with(context)
                    .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_team)
                    .animate(android.R.anim.fade_in)
                    .listener(new SvgSoftwareLayerSetter<Uri>());
            Uri uri = Uri.parse(url);
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri)
                    .into(imgLogo1);
        } else {

            Glide.with(context).load(url).into(imgLogo1);

        }
    }


    public String getShortTeamName(String teamFull, List<TeamDataBean> teamDataList) {


        TeamDataBean team = getTeamByName(teamFull, teamDataList);

        if (team != null) {

            return team.getCode() != null ? team.getCode() : team.getShortName();

        } else {
            return teamFull.trim().substring(0, 7);
        }
    }


    public TeamDataBean getTeamByName(String teamFull, List<TeamDataBean> teamDataList) {

        for (TeamDataBean teamAPIBean : teamDataList) {

            if (teamFull.equalsIgnoreCase(teamAPIBean.getName())) {
                return teamAPIBean;
            }
        }
        return null;
    }

    public static int getTextColor(int point){
        if(point > 0)
            return Color.rgb(106,198,57);
        else if(point < 0)
            return Color.RED ;

        return Color.LTGRAY ;

    }

    public String getLogoByName(String teamFull, List<TeamDataBean> teamDataList) {

        TeamDataBean team = getTeamByName(teamFull, teamDataList);

        if (team != null) {
            return team.getCrestUrl();
            //return team.getCrestUrl();

        } else {
            return "";


        }
    }


}
