package com.longngo.footballfan.data.source;

/**
 * Created by Long on 10/6/2016.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.ui.FootballFanApplication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
public class CompetitionsRepository implements CompetitionsDataSource {
    private static final String TAG = "CompetitionsRepository";
    @Nullable
    private static CompetitionsRepository INSTANCE = null;

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
    private CompetitionsRepository(@NonNull CompetitionsDataSource competitionsRemoteDataSource,
                            @NonNull CompetitionsDataSource competitionsLocalDataSource) {
        mCompetitionsRemoteDataSource = competitionsRemoteDataSource;
        mCompetitionsLocalDataSource = competitionsLocalDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param competitionsRemoteDataSource the backend data source
     * @param competitionsLocalDataSource  the device storage data source
     * @return the {@link CompetitionsRepository} instance
     */
    public static CompetitionsRepository getInstance(@NonNull CompetitionsDataSource competitionsRemoteDataSource,
                                              @NonNull CompetitionsDataSource competitionsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionsRepository(competitionsRemoteDataSource, competitionsLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(CompetitionsDataSource, CompetitionsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     */


    @Override
    public Observable<List<Competition>> getCompetitions() {
        // Respond immediately with cache if available and not dirty
        if (mCachedCompetitions != null && !mCacheIsDirty) {
            return Observable.from(mCachedCompetitions.values()).toList();
        } else if (mCachedCompetitions == null) {
            mCachedCompetitions = new LinkedHashMap<>();
        }

        Observable<List<Competition>> remoteTasks = getAndSaveRemoteCompetitions();
        return remoteTasks;
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
                        public Observable<List<Competition>> call(List<Competition> tasks) {
                            return Observable.from(tasks)
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
