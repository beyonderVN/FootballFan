package com.longngo.footballfan.data.model;

import java.io.Serializable;

/**
 * Created by Long on 10/10/2016.
 */

public class Standing implements Serializable {
    private String goals;

    private String crestURI;

    private String rank;

    private String playedGames;

    private String goalDifference;

    private String goalsAgainst;

    private String points;

    private String team;

    private String teamId;

    public String getGoals ()
    {
        return goals;
    }

    public void setGoals (String goals)
    {
        this.goals = goals;
    }

    public String getCrestURI ()
    {
        return crestURI;
    }

    public void setCrestURI (String crestURI)
    {
        this.crestURI = crestURI;
    }

    public String getRank ()
    {
        return rank;
    }

    public void setRank (String rank)
    {
        this.rank = rank;
    }

    public String getPlayedGames ()
    {
        return playedGames;
    }

    public void setPlayedGames (String playedGames)
    {
        this.playedGames = playedGames;
    }

    public String getGoalDifference ()
    {
        return goalDifference;
    }

    public void setGoalDifference (String goalDifference)
    {
        this.goalDifference = goalDifference;
    }

    public String getGoalsAgainst ()
    {
        return goalsAgainst;
    }

    public void setGoalsAgainst (String goalsAgainst)
    {
        this.goalsAgainst = goalsAgainst;
    }

    public String getPoints ()
    {
        return points;
    }

    public void setPoints (String points)
    {
        this.points = points;
    }

    public String getTeam ()
    {
        return team;
    }

    public void setTeam (String team)
    {
        this.team = team;
    }

    public String getTeamId ()
    {
        return teamId;
    }

    public void setTeamId (String teamId)
    {
        this.teamId = teamId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [goals = "+goals+", crestURI = "+crestURI+", rank = "+rank+", playedGames = "+playedGames+", goalDifference = "+goalDifference+", goalsAgainst = "+goalsAgainst+", points = "+points+", team = "+team+", teamId = "+teamId+"]";
    }
}
