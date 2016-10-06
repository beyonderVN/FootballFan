package com.longngo.footballfan.ui.viewmodel;

import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.ui.adapter.vmfactory.VMTypeFactory;
import com.longngo.footballfan.ui.adapter.vmfactory.Visitable;

import java.io.Serializable;

/**
 * Created by Admin on 06/10/2016.
 */

public class TeamVM implements Serializable,Visitable {

    Team team;

    public TeamVM(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int getVMType(VMTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

    @Override
    public String toString() {
        return team.toString();
    }
}
