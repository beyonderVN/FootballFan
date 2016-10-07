package com.longngo.footballfan.ui.viewmodel;

import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.ui.adapter.vmfactory.VMTypeFactory;

/**
 * Created by Admin on 06/10/2016.
 */

public class TeamVM extends BaseVM {

    private Team team;

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
