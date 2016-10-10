package com.longngo.footballfan.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.viewmodel.SimpleTextViewVM;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 */

public class SimpleTextViewHolder extends BaseViewHolder<SimpleTextViewVM> {

    @BindView(R.id.des)
    TextView des;
    SimpleTextViewVM competitionVM;
    public SimpleTextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(SimpleTextViewVM item) {
        competitionVM = item;
        des.setText(competitionVM.toString());

    }
}
