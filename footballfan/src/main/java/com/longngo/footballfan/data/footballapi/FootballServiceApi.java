package com.longngo.footballfan.data.footballapi;


import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Single;

public interface FootballServiceApi {

    /**
     * Retrieve a list of competitions
     */
    @GET("competitions")
    Single<List<Competition>> getCompetitions(@Query("season") String season);

    @GET("competitions?season=2016")
    Observable<List<Competition>> getCompetitions();


    @GET("/v1/competitions/{id}/teams")
    Observable<List<Team>> getTeams(@Path("id") int teamId);



}
