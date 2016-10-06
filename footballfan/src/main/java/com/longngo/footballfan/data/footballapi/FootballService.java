package com.longngo.footballfan.data.footballapi;


import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Single;

@Singleton
public class FootballService {

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
        return mFootballServiceApi.getTeams(competitionId);
    }

}