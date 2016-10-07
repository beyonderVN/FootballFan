package com.longngo.footballfan.data.backend.footballapi;


import android.util.Log;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.model.TeamList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Single;
import rx.functions.Func1;

@Singleton
public class FootballService {
    private static final String TAG = "FootballService";

    private final FootballServiceApi mFootballServiceApi;

    @Inject
    public FootballService(FootballServiceApi footballServiceApi) {
        mFootballServiceApi = footballServiceApi;
    }

    public Single<List<Competition>> getCompetitions(String season) {
        return mFootballServiceApi.getCompetitions(season);
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

}