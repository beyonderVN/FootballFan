package com.longngo.footballfan.data.source.remote;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;

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

}
