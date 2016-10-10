package com.longngo.footballfan.data.source.remote;

import com.longngo.footballfan.data.model.FixtureList;
import com.longngo.footballfan.data.model.LeagueTable;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsDataSource;
import com.longngo.footballfan.FootballFanApplication;

import java.util.List;

import rx.Observable;

/**
 * Created by Admin on 06/10/2016.
 */

public class TeamsRemoteDataSource implements TeamsDataSource {
    private static TeamsRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    public static TeamsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeamsRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TeamsRemoteDataSource() {}
    @Override
    public Observable<List<Team>> getTeams(int competitionId) {
        return FootballFanApplication.getMainComponent().dataManager().getTeams(competitionId);
    }

    @Override
    public Observable<LeagueTable> getLeagueTable(int competitionId) {
        return FootballFanApplication.getMainComponent().dataManager().getLeagueTable(competitionId);
    }

    @Override
    public Observable<FixtureList> getFixtureList(int competitionId) {
        return FootballFanApplication.getMainComponent().dataManager().getFixtureList(competitionId);
    }

}
