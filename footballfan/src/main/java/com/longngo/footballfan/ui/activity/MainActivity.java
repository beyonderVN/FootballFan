package com.longngo.footballfan.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.longngo.footballfan.R;
import com.longngo.footballfan.coremvp.MVPActivity;
import com.longngo.footballfan.data.footballapi.FootballService;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.CompetitionsDataSource;
import com.longngo.footballfan.data.source.CompetitionsRepository;
import com.longngo.footballfan.data.source.local.CompetitionsLocalDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.ui.adapter.CompetitionListAdapter;
import com.longngo.footballfan.ui.base.BaseActivity;
import com.longngo.footballfan.ui.viewmodel.mapper.Mapper;
import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;
import com.longngo.footballfan.util.schedulers.BaseSchedulerProvider;
import com.longngo.footballfan.util.schedulers.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity<MainPresentationModel,MainView,MainPresenter> implements MainView{
    private static final String TAG = "MainActivity";

    @BindView(R.id.list)
    RecyclerView listRV;

    CompetitionListAdapter competitionListAdapter;

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
        competitionListAdapter = new CompetitionListAdapter(presenter.getPresentationModel().visitableList);
        listRV.setAdapter(competitionListAdapter);
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
        competitionListAdapter.notifyDataSetChanged();
    }
}
