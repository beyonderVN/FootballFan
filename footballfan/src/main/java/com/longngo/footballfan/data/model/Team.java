package com.longngo.footballfan.data.model;

import java.io.Serializable;

/**
 * Created by Admin on 06/10/2016.
 */

public class Team implements Serializable {
    private String id;

    private String squadMarketValue;

    private String crestUrl;

    private String name;

    private String shortName;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSquadMarketValue ()
    {
        return squadMarketValue;
    }

    public void setSquadMarketValue (String squadMarketValue)
    {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl ()
    {
        return crestUrl;
    }

    public void setCrestUrl (String crestUrl)
    {
        this.crestUrl = crestUrl;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getShortName ()
    {
        return shortName;
    }

    public void setShortName (String shortName)
    {
        this.shortName = shortName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", squadMarketValue = "+squadMarketValue+", crestUrl = "+crestUrl+", name = "+name+", shortName = "+shortName+"]";
    }
}
