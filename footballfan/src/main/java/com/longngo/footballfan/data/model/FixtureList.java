package com.longngo.footballfan.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Long on 10/10/2016.
 */

public class FixtureList implements Serializable {
    private List<Fixture> fixtures;

    private String count;

    public List<Fixture> getFixtures ()
    {
        return fixtures;
    }

    public void setFixtures (List<Fixture> fixtures)
    {
        this.fixtures = fixtures;
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
        return "ClassPojo [fixtures = "+fixtures+", count = "+count+"]";
    }
}
