package com.longngo.footballfan.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.longngo.footballfan.R;
import com.longngo.footballfan.data.footballapi.FootballService;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.CompetitionsRepository;
import com.longngo.footballfan.data.source.local.CompetitionsLocalDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.ui.adapter.CompetitionListAdapter;
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

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    BaseSchedulerProvider baseSchedulerProvider;
    @BindView(R.id.list)
    RecyclerView listRV;
    FootballService mFootballService;
    CompetitionListAdapter competitionListAdapter;
    List<Visitable> visitables = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFootballService = FootballFanApplication.getMainComponent().dataManager();
        listRV.setLayoutManager( new LinearLayoutManager(this));
        competitionListAdapter = new CompetitionListAdapter(visitables);
        listRV.setAdapter(competitionListAdapter);

        baseSchedulerProvider = FootballFanApplication.getMainComponent().schedulerProvider();
        getCompetitions("2016");


    }

    @Inject
    SchedulerProvider schedulerProvider;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public void getCompetitions(String season) {
        mSubscriptions.clear();
        Subscription subscription = CompetitionsRepository.getInstance(CompetitionRemoteDataSource.getInstance(),
                CompetitionsLocalDataSource.getInstance(this, baseSchedulerProvider))
                .getCompetitions()
                .map(new Func1<List<Competition>, List<Visitable>>() {
                    @Override
                    public List<Visitable> call(List<Competition> competitions) {
                        return Mapper.tran(competitions);
                    }
                })
                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<Visitable>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Visitable> competitions) {
                        Log.d(TAG, "onNext: "+competitions.size());
                        if (!competitions.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+competitions.size());
                            visitables.addAll(competitions);
                            competitionListAdapter.notifyDataSetChanged();

                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}
