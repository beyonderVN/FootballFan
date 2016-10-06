package com.longngo.footballfan.data.source;

/**
 * Created by Long on 10/6/2016.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.local.CompetitionsLocalDataSource;
import com.longngo.footballfan.data.source.local.TeamsLocalDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;
import com.longngo.footballfan.data.source.remote.TeamsRemoteDataSource;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Concrete implementation to load data from the data sources into a cache.
 * <p/>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
@Singleton
public class TeamsRepository implements TeamsDataSource {

    private static final String TAG = "CompetitionsRepository";

    @NonNull
    private final TeamsDataSource teamsRemoteDataSource;

    @NonNull
    private final TeamsDataSource teamsLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    Map<String, Competition> mCachedCompetitions;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    @Inject
    public TeamsRepository() {
        teamsRemoteDataSource = TeamsRemoteDataSource.getInstance();
        teamsLocalDataSource = TeamsLocalDataSource.getInstance();
    }


    @Override
        public Observable<List<Team>> getTeams(int competitionId) {
        return teamsRemoteDataSource.getTeams(competitionId);
    }
}
