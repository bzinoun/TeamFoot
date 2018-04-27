package com.bzinoun.premierleaguenews.service;

import com.bzinoun.premierleaguenews.model.data.TeamDataBean;
import com.bzinoun.premierleaguenews.model.data.TeamDataDao;
import com.bzinoun.premierleaguenews.model.team.Team;
import com.bzinoun.premierleaguenews.utils.TeamDataMapper;

import java.util.List;

/**
 * Created by hungvu on 8/27/2017.
 * Project PremierLeagueNews
 * Package com.bzinoun.premierleaguenews.utils
 */

public class TeamDataService {

    private static TeamDataService teamDataService;

    public static TeamDataService getInstance() {
        if (teamDataService == null)
            teamDataService = new TeamDataService();
        return teamDataService;
    }


    public List<TeamDataBean> saveList(List<Team> list, TeamDataDao teamDataDao) {
        TeamDataMapper teamDataMapper = new TeamDataMapper();

        List<TeamDataBean> teamDataList = teamDataMapper.fromStandingToTeamData(list);
        teamDataDao.save(teamDataList);

        return teamDataList;
    }

    public TeamDataBean getTeam(TeamDataDao teamDataDao, String code) {
        List<TeamDataBean> teamDataBean = teamDataDao.findByCode(code);

        if (teamDataBean.size() > 0) {
            TeamDataBean teamDataBean1 = teamDataBean.get(0);
            return teamDataBean1;
        }

        return null;
    }

}
