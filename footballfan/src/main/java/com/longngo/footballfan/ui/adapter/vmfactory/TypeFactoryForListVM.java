package com.longngo.footballfan.ui.adapter.vmfactory;

import android.view.View;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.CompetitionViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.TeamViewHolder;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.viewmodel.TeamVM;

/**
 * Created by Long on 10/5/2016.
 */

public class TypeFactoryForListVM implements VMTypeFactory {

    @Override
    public int getType(CompetitionVM competitionVM) {
        return R.layout.layout_item_competition;
    }

    @Override
    public int getType(TeamVM teamVM) {
        return R.layout.layout_item_team;
    }

    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case R.layout.layout_item_competition :
                return new CompetitionViewHolder(view);
            case R.layout.layout_item_team :
                return new TeamViewHolder(view);

        }
        return null;
    }
}
