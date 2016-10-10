package com.longngo.footballfan.ui.adapter.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.adapter.BaseAdapter;
import com.longngo.footballfan.ui.viewmodel.SectionVM;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngo.footballfan.FootballFanApplication.mContext;

/**
 * Created by Long on 10/10/2016.
 */

public class SectionHolder extends BaseViewHolder<SectionVM>{

private static final String TAG = "SectionViewHolder";
    @BindView(R.id.itemTitle)
TextView itemTitle;
    @BindView(R.id.recycler_view_list)
RecyclerView recycler_view_list;
    @BindView(R.id.btnMore)
Button btnMore;



public SectionHolder(View itemView) {
        super(itemView);
    ButterKnife.bind(this, itemView);

        }

    @Override
    public void bind(SectionVM item) {
        final String sectionName = item.getTitle();

        List singleSectionItems = item.getBaseVMs();
        Log.d(TAG, "bindSectionView: "+singleSectionItems.size());
        itemTitle.setText(sectionName);

        BaseAdapter baseAdapter = new BaseAdapter(singleSectionItems);
        recycler_view_list.setNestedScrollingEnabled(false);
        recycler_view_list.setHasFixedSize(true);
        recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recycler_view_list.setAdapter(baseAdapter);


        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();



            }
        });
    }

}