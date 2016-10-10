package com.longngo.footballfan.data.backend.footballapi;


import android.util.Log;

import com.google.gson.JsonObject;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.FixtureList;
import com.longngo.footballfan.data.model.LeagueTable;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.model.TeamList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class FootballService {
    private static final String TAG = "FootballService";

    private final FootballServiceApi mFootballServiceApi;

    @Inject
    public FootballService(FootballServiceApi footballServiceApi) {
        mFootballServiceApi = footballServiceApi;
    }


    public Observable<List<Competition>> getCompetitions() {
        return mFootballServiceApi.getCompetitions();
    }

    public Observable<List<Team>> getTeams(int competitionId) {
        return mFootballServiceApi.getTeams(competitionId)
                .map(new Func1<TeamList, List<Team>>() {
                    @Override
                    public List<Team> call(TeamList teams) {
                        Log.d(TAG, "getTeams -> call: "+teams.toString());

                        return teams.getTeams();
                    }
                });
    }

    public Observable<LeagueTable> getLeagueTable(int competitionId) {
        return mFootballServiceApi.getLeagueTable(competitionId).map(new Func1<JsonObject, LeagueTable>() {
            @Override
            public LeagueTable call(JsonObject teams) {
                Log.d(TAG, "getLeagueTable -> call: "+teams.toString());

                return null;
            }
        });
    }

    public Observable<FixtureList> getFixtureList(int competitionId) {
        return mFootballServiceApi.getFixtureList(competitionId,8);
    }
}