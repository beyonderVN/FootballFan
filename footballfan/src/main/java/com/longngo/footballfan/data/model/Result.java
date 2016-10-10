package com.longngo.footballfan.data.model;

import java.io.Serializable;

/**
 * Created by Long on 10/10/2016.
 */

public class Result implements Serializable{
    private String goalsHomeTeam;

    private String goalsAwayTeam;

    public String getGoalsHomeTeam ()
    {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam (String goalsHomeTeam)
    {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public String getGoalsAwayTeam ()
    {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam (String goalsAwayTeam)
    {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [goalsHomeTeam = "+goalsHomeTeam+", goalsAwayTeam = "+goalsAwayTeam+"]";
    }
}
