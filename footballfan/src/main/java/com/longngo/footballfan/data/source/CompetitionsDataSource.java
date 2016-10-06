package com.longngo.footballfan.data.source;

import com.longngo.footballfan.data.model.Competition;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 10/6/2016.
 */

public interface CompetitionsDataSource {
    Observable<List<Competition>> getCompetitions();

    void saveCompetition(Competition competition);
}
