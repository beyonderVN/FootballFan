package com.longngo.footballfan.data.source;

/**
 * Created by Long on 10/6/2016.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.local.CompetitionsLocalDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Concrete implementation to load data from the data sources into a cache.
 * <p/>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
@Singleton
public class CompetitionsRepository implements CompetitionsDataSource {
    private static final String TAG = "CompetitionsRepository";

    @NonNull
    private final CompetitionsDataSource mCompetitionsRemoteDataSource;

    @NonNull
    private final CompetitionsDataSource mCompetitionsLocalDataSource;

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
    public CompetitionsRepository() {
        mCompetitionsRemoteDataSource = CompetitionRemoteDataSource.getInstance();
        mCompetitionsLocalDataSource = CompetitionsLocalDataSource.getInstance();
    }



    @Override
    public Observable<List<Competition>> getCompetitions() {
        // Respond immediately with cache if available and not dirty
        if (mCachedCompetitions != null && !mCacheIsDirty && mCachedCompetitions.values().size()!=0) {
            return Observable.from(mCachedCompetitions.values()).toList();
        } else if (mCachedCompetitions == null) {
            mCachedCompetitions = new LinkedHashMap<>();
        }

        Observable<List<Competition>> remoteCompetitions = getAndSaveRemoteCompetitions();
        return remoteCompetitions;
//        if (mCacheIsDirty) {
//            return remoteTasks;
//        } else {
//            // Query the local storage if available. If not, query the network.
//            Observable<List<Competition>> localTasks = getAndCacheLocalCompetitions();
//            return Observable.concat(localTasks, remoteTasks)
//                    .filter(new Func1<List<Competition>, Boolean>() {
//                        @Override
//                        public Boolean call(List<Competition> tasks) {
//                            return !tasks.isEmpty();
//                        }
//                    }).first();
//        }
    }

    @Override
    public void saveCompetition(Competition competition) {

    }

    private Observable<List<Competition>> getAndCacheLocalCompetitions() {
            return mCompetitionsLocalDataSource.getCompetitions()
                    .flatMap(new Func1<List<Competition>, Observable<List<Competition>>>() {
                        @Override
                        public Observable<List<Competition>> call(List<Competition> competitions) {
                            return Observable.from(competitions)
                                    .doOnNext(new Action1<Competition>() {
                                        @Override
                                        public void call(Competition competition) {
                                            mCachedCompetitions.put(competition.getId(), competition);
                                        }
                                    })
                                    .toList();
                        }
                    });
        }

        private Observable<List<Competition>> getAndSaveRemoteCompetitions() {
            return mCompetitionsRemoteDataSource
                    .getCompetitions()
                    .flatMap(new Func1<List<Competition>, Observable<List<Competition>>>() {
                        @Override
                        public Observable<List<Competition>> call(List<Competition> competitions) {
                            return Observable.from(competitions).doOnNext(new Action1<Competition>() {
                                @Override
                                public void call(Competition competition) {
                                    Log.d(TAG, "getAndSaveRemoteCompetitions: "+competition.toString());
//                                    mCompetitionsLocalDataSource.saveCompetition(competition);
//                                    mCachedCompetitions.put(competition.getId(), competition);
                                }
                            }).toList();
                        }
                    })
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {
                            mCacheIsDirty = false;
                        }
                    });

        }

}
