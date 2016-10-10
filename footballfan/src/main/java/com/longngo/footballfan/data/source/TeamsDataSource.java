package com.longngo.footballfan.data.source;

import com.longngo.footballfan.data.model.FixtureList;
import com.longngo.footballfan.data.model.LeagueTable;
import com.longngo.footballfan.data.model.Team;

import java.util.List;

import rx.Observable;

/**
 * Created by Admin on 06/10/2016.
 */

public interface TeamsDataSource {
    Observable<List<Team>> getTeams(int competitionId);

    Observable<LeagueTable> getLeagueTable(int competitionId);

    Observable<FixtureList> getFixtureList(int competitionId);
}
