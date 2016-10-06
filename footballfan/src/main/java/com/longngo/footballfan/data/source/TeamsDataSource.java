package com.longngo.footballfan.data.source;

import android.widget.TextView;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;

import java.util.List;

import rx.Observable;

/**
 * Created by Admin on 06/10/2016.
 */

public interface TeamsDataSource {
    Observable<List<Team>> getTeams(int competitionId);
}
