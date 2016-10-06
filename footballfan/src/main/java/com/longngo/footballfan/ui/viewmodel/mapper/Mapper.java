package com.longngo.footballfan.ui.viewmodel.mapper;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.adapter.vmfactory.Visitable;
import com.longngo.footballfan.ui.viewmodel.TeamVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 10/5/2016.
 */

public class Mapper {
    public static Visitable tranCompetition(Competition competition){
        return new CompetitionVM(competition);
    }

    public static List<Visitable> tranCompetition(List<Competition> competitions){
        List<Visitable> list = new ArrayList<>();
        for (Competition item :competitions) {
            list.add(tranCompetition(item));
        }
        return list;
    }

    public static Visitable tranTeam(Team team){
        return new TeamVM(team);
    }

    public static List<Visitable> tranTeam(List<Team> teams){
        List<Visitable> list = new ArrayList<>();
        for (Team item :teams) {
            list.add(tranTeam(item));
        }
        return list;
    }


}
