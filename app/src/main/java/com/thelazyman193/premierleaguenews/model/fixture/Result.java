
package com.thelazyman193.premierleaguenews.model.fixture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("goalsHomeTeam")
    @Expose
    private int goalsHomeTeam;
    @SerializedName("goalsAwayTeam")
    @Expose
    private int goalsAwayTeam;
    @SerializedName("halfTime")
    @Expose
    private HalfTime halfTime;

    public Object getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public Object getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

}
