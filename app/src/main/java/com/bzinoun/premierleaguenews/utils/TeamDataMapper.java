package com.bzinoun.premierleaguenews.utils;

import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.team.Self_;
import com.bzinoun.premierleaguenews.model.team.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungvu on 8/27/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.utils
 */

public class TeamDataMapper {

    private static TeamDataMapper teamDataMapper;

    public static TeamDataMapper getInstance() {
        if (teamDataMapper == null)
            teamDataMapper = new TeamDataMapper();
        return teamDataMapper;
    }


    public List<TeamDataBean> fromStandingToTeamData(List<Team> standingList) {
        List<TeamDataBean> listTeamData = new ArrayList<>();
        for (Team vTeam : standingList) {
            TeamDataBean team = new TeamDataBean();
            team.setName(vTeam.getName());
            team.setCode(vTeam.getCode());
            team.setShortName(vTeam.getShortName());
            team.setCrestUrl(vTeam.getCrestUrl());

            Self_ self = vTeam.getLinks().getSelf();
            String url = self.getHref();
            String[] parts = url.split("/");
            String id = parts[parts.length-1] ;
            team.setId(id);
            listTeamData.add(team);


        }
        return listTeamData;

    }

    public List<Team> fromeamDataToStanding(List<TeamDataBean> teamDataBeanList) {
        List<Team> listTeamStanding = new ArrayList<>();
        for (TeamDataBean vTeam : teamDataBeanList) {
            Team team = new Team();
            team.setName(vTeam.getName());
            team.setCode(vTeam.getCode());
            team.setShortName(vTeam.getShortName());
            team.setCrestUrl(vTeam.getCrestUrl());

            listTeamStanding.add(team);

        }
        return listTeamStanding;

    }

}
