package com.longngo.footballfan.ui.adapter.vmfactory;

import android.view.View;

import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.viewmodel.SectionVM;
import com.longngo.footballfan.ui.viewmodel.SimpleTextViewVM;
import com.longngo.footballfan.ui.viewmodel.TeamVM;

/**
 * Created by Long on 10/5/2016.
 */

public interface VMTypeFactory {
    int getType(CompetitionVM competitionVM);
    int getType(TeamVM teamVM);

    BaseViewHolder createHolder(int type, View view);

    int getType(SectionVM sectionVM);

    int getType(SimpleTextViewVM simpleTextViewVM);
}
