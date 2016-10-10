package com.longngo.footballfan.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Long on 10/10/2016.
 */

public class LeagueTable implements Serializable{
    private String leagueCaption;

    private String matchday;

    private List<Standing> standing;

    public String getLeagueCaption ()
    {
        return leagueCaption;
    }

    public void setLeagueCaption (String leagueCaption)
    {
        this.leagueCaption = leagueCaption;
    }

    public String getMatchday ()
    {
        return matchday;
    }

    public void setMatchday (String matchday)
    {
        this.matchday = matchday;
    }

    public List<Standing> getStanding ()
    {
        return standing;
    }

    public void setStanding (List<Standing> standing)
    {
        this.standing = standing;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [leagueCaption = "+leagueCaption+", matchday = "+matchday+", standing = "+standing+"]";
    }
}
