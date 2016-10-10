package com.longngo.footballfan.data.model;

import java.io.Serializable;

/**
 * Created by Long on 10/10/2016.
 */

public class Fixture implements Serializable {
    private String id;

    private Result result;

    private String awayTeamId;

    private String homeTeamId;

    private String matchday;

    private String awayTeamName;

    private String date;

    private String competitionId;

    private String homeTeamName;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Result getResult ()
    {
        return result;
    }

    public void setResult (Result result)
    {
        this.result = result;
    }

    public String getAwayTeamId ()
    {
        return awayTeamId;
    }

    public void setAwayTeamId (String awayTeamId)
    {
        this.awayTeamId = awayTeamId;
    }

    public String getHomeTeamId ()
    {
        return homeTeamId;
    }

    public void setHomeTeamId (String homeTeamId)
    {
        this.homeTeamId = homeTeamId;
    }

    public String getMatchday ()
    {
        return matchday;
    }

    public void setMatchday (String matchday)
    {
        this.matchday = matchday;
    }

    public String getAwayTeamName ()
    {
        return awayTeamName;
    }

    public void setAwayTeamName (String awayTeamName)
    {
        this.awayTeamName = awayTeamName;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getCompetitionId ()
    {
        return competitionId;
    }

    public void setCompetitionId (String competitionId)
    {
        this.competitionId = competitionId;
    }

    public String getHomeTeamName ()
    {
        return homeTeamName;
    }

    public void setHomeTeamName (String homeTeamName)
    {
        this.homeTeamName = homeTeamName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", result = "+result+", awayTeamId = "+awayTeamId+", homeTeamId = "+homeTeamId+", matchday = "+matchday+", awayTeamName = "+awayTeamName+", date = "+date+", competitionId = "+competitionId+", homeTeamName = "+homeTeamName+"]";
    }
}
