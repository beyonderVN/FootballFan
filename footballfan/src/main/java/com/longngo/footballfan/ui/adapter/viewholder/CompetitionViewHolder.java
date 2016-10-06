package com.longngo.footballfan.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.activity.Navigator;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Long on 10/5/2016.
 */

public class CompetitionViewHolder extends BaseViewHolder<CompetitionVM> {

    @BindView(R.id.des)
    TextView des;
    CompetitionVM competitionVM;
    public CompetitionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(CompetitionVM item) {
        competitionVM = item;
        des.setText(competitionVM.toString());
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.navigateToCompetionDetailActivity(v.getContext(),competitionVM.getCompetition());
            }
        });
    }
}
