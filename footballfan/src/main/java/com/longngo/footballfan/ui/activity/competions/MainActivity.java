package com.longngo.footballfan.ui.activity.competions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.ui.adapter.BaseAdapter;
import com.longngo.footballfan.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresentationModel,MainView,MainPresenter> implements MainView{
    private static final String TAG = "CompetionDetailActivity";

    @BindView(R.id.list)
    RecyclerView listRV;

    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listRV.setLayoutManager( new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseAdapter = new BaseAdapter(presenter.getPresentationModel().visitableList);
        listRV.setAdapter(baseAdapter);
    }

    @Override
    protected void performFieldInjection() {
        FootballFanApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected MainPresentationModel createPresentationModel() {
        return new MainPresentationModel();
    }


    @Override
    public void loadCompetitions() {
        baseAdapter.notifyDataSetChanged();
    }
}