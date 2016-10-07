package com.longngo.footballfan.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Long on 10/7/2016.
 */

public class TeamList implements Serializable
{
    private List<Team> teams;

    private String count;

    public List<Team> getTeams ()
    {
        return teams;
    }

    public void setTeams (List<Team> teams)
    {
        this.teams = teams;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [teams = "+teams+", count = "+count+"]";
    }
}
