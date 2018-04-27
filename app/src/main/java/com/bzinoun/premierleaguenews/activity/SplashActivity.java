package com.bzinoun.premierleaguenews.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bzinoun.premierleaguenews.BaseActivity;
import com.bzinoun.premierleaguenews.R;
import com.bzinoun.premierleaguenews.model.data.RealmManager;
import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.data.TeamDataDao;
import com.bzinoun.premierleaguenews.model.team.Team;
import com.bzinoun.premierleaguenews.model.team.TeamAPIBean;
import com.bzinoun.premierleaguenews.retrofit.FBAPIService;
import com.bzinoun.premierleaguenews.service.TeamDataService;
import com.bzinoun.premierleaguenews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    public static final int MODULO = 1;
    private static final int ALERT_DIALOG1 = 1;
    private static final int ALERT_DIALOG2 = 2;
    private static final String TAG = "SplashActivity";
    private static String PACKAGE_NAME = "topic";
    ProgressBar spinner;
    private Utils utils = Utils.getInstance();
    private TeamDataService teamDataService = TeamDataService.getInstance();
    private FBAPIService mService;
    private List<TeamDataBean> teamDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        spinner = (ProgressBar) findViewById(R.id.pbHeaderProgress);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        mService = utils.getFABAPIService();
        RealmManager.incrementCount();
        final TeamDataDao teamDataDao = new TeamDataDao(RealmManager.getRealm());
        teamDataList = teamDataDao.findAll();
        Log.d("PATH", "path: " + teamDataDao.getPath());


        if (teamDataList.size() <= 1) {
            getTeams(teamDataDao);
        } else {
            spinner.setVisibility(View.GONE);


        }


    }

    private void getTeams(final TeamDataDao teamDataDao) {
        spinner.setVisibility(View.VISIBLE);
        mService.getLeagueTeam(getString(R.string.token), Utils.getLeagueId()).enqueue(new Callback<TeamAPIBean>() {
            @Override
            public void onResponse(Call<TeamAPIBean> call, Response<TeamAPIBean> response) {

                List<Team> teamList = response.body().getTeams();
                teamDataList = teamDataService.saveList(teamList, teamDataDao);
                spinner.setVisibility(View.GONE);
                Log.d("ChartFrag", "End Success getLeagueTeam " + "");


            }

            @Override
            public void onFailure(Call<TeamAPIBean> call, Throwable t) {
                Log.d("ChartFrag", t.toString() + "");
                spinner.setVisibility(View.GONE);

            }
        });
    }


    public void onClickStart(View v) {
        //AdsUtils.showInterstitial(1,mInterstitialAd);

        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);


        //startActivity(intent);
        finish();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Message Dialog");
        builder.setTitle("Title Dialog");
        builder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        dialog = builder.create();

        return dialog;

    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Log.v("Splash", "Internet Connection Not Present");
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        RealmManager.decrementCount();

        super.onDestroy();
    }
}
