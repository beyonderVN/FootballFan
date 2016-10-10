package com.longngo.footballfan.ui.adapter.vmfactory;

import android.view.View;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.CompetitionViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.SectionHolder;
import com.longngo.footballfan.ui.adapter.viewholder.SimpleTextViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.TeamViewHolder;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.viewmodel.SectionVM;
import com.longngo.footballfan.ui.viewmodel.SimpleTextViewVM;
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
            case R.layout.layout_section :
                return new SectionHolder(view);
            case R.layout.layout_simple :
                return new SimpleTextViewHolder(view);

        }
        return null;
    }

    @Override
    public int getType(SectionVM sectionVM) {
        return  R.layout.layout_section;
}

    @Override
    public int getType(SimpleTextViewVM simpleTextViewVM) {
        return  R.layout.layout_simple;
    }
}
