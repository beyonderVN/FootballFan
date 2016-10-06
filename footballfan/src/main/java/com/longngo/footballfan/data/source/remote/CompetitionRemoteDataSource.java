package com.longngo.footballfan.data.source.remote;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.CompetitionsDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 10/6/2016.
 */

public class CompetitionRemoteDataSource implements CompetitionsDataSource {
    private static CompetitionRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    public static CompetitionRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private CompetitionRemoteDataSource() {}
    @Override
    public Observable<List<Competition>> getCompetitions() {
        return FootballFanApplication.getMainComponent().dataManager().getCompetitions();
    }

    @Override
    public void saveCompetition(Competition competition) {

    }
}
