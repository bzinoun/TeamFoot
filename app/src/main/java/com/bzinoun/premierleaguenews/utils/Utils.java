package com.bzinoun.premierleaguenews.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.retrofit.RetrofitClient;

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

    public int getLogoByName(String teamFull, List<TeamDataBean> teamDataList) {

        TeamDataBean team = getTeamByName(teamFull, teamDataList);

        if (team != null) {
            return R.mipmap.ic_chel;
            //return team.getCrestUrl();

        } else {
            return R.mipmap.ic_news;


        }
    }


    public String getShortTeamName(String teamFull, List<TeamDataBean> teamDataList) {


        TeamDataBean team = getTeamByName(teamFull, teamDataList);

        if (team != null) {

            return team.getCode();

        } else {
            return teamFull.substring(0, 3);
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



}
