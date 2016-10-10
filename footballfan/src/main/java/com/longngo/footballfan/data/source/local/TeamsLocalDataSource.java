package com.longngo.footballfan.data.source.local;

import android.support.annotation.Nullable;

import com.longngo.footballfan.data.model.FixtureList;
import com.longngo.footballfan.data.model.LeagueTable;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by Admin on 06/10/2016.
 */

public class TeamsLocalDataSource implements TeamsDataSource {

    @Nullable
    private static TeamsLocalDataSource INSTANCE;

    public static TeamsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeamsLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
    @Override
    public Observable<List<Team>> getTeams(int competitionId) {
        return null;
    }

    @Override
    public Observable<LeagueTable> getLeagueTable(int competitionId) {
        return null;
    }

    @Override
    public Observable<FixtureList> getFixtureList(int competitionId) {
        return null;
    }
}
