package com.bzinoun.premierleaguenews.retrofit;

import com.bzinoun.premierleaguenews.model.detailfixture.FixtureBean;
import com.bzinoun.premierleaguenews.model.fixture.FixtureAPIBean;
import com.bzinoun.premierleaguenews.model.player.PlayerApiBean;
import com.bzinoun.premierleaguenews.model.premierleagueteam.PremierLeagueRank;
import com.bzinoun.premierleaguenews.model.team.TeamAPIBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by hungvu on 8/29/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.retrofit
 */

public interface FBAPIService {
    //get all table leauge premier 2017-18
    @GET("/v1/competitions/{teamid}/leagueTable")
    Call<PremierLeagueRank> getTeamRank(@Header("X-Auth-Token") String token, @Path("teamid") String teamid);

    //get all table leauge premier 2017-18
    @GET("/v1/competitions/{leagueId}/teams")
    Call<TeamAPIBean> getLeagueTeam(@Header("X-Auth-Token") String token, @Path("leagueId") String teamid);

    //get player
    @GET("/v1/teams/{teamid}/players")
    Call<PlayerApiBean> getPlayerList(@Header("X-Auth-Token") String token,
                                      @Path("teamid") int teamid);

    //get all fixtures
    @GET("/v1/competitions/{leagueid}/fixtures")
    Call<FixtureAPIBean> getAllFixture(@Header("X-Auth-Token") String token,
                                       @Path("leagueid") String teamid,
                                       @Query("timeFrame") String timeFrame);


    //get player
    @GET("/v1/teams/{teamid}/fixtures")
    Call<FixtureAPIBean> getNextLastFixture(@Header("X-Auth-Token") String token,
                                            @Path("teamid") int teamid,
                                            @Query("timeFrame") String timeFrame);

    @GET
    Call<FixtureBean> getCurrentFixture(
            @Url String linkFixture,
            @Header("X-Auth-Token") String token
    );


}
