package com.longngo.footballfan.ui.activity.competion;

import android.util.Log;

import com.longngo.footballfan.coremvp.SimpleMVPPresenter;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsRepository;
import com.longngo.footballfan.ui.viewmodel.mapper.Mapper;
import com.longngo.footballfan.ui.adapter.vmfactory.Visitable;
import com.longngo.footballfan.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 06/10/2016.
 */

public class CompetionDetailPresenter extends SimpleMVPPresenter<CompetionDetailView,CompetionDetailPresentationModel> implements CompetionDetailView {
    private static final String TAG = "CompetionPresenter";
    BaseSchedulerProvider baseSchedulerProvider;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    TeamsRepository teamsRepository;
    @Inject
    public CompetionDetailPresenter(BaseSchedulerProvider baseSchedulerProvider, TeamsRepository teamsRepository) {
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public void attachView(CompetionDetailView mvpView, CompetionDetailPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

    }

    public void getCompetition(int id) {
        mSubscriptions.clear();
        Subscription subscription = teamsRepository
                .getTeams(id)

                .map(new Func1<List<Team>, List<Visitable>>() {
                    @Override
                    public List<Visitable> call(List<Team> teams) {
                        return Mapper.tranTeam(teams);
                    }
                })
                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<Visitable>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(List<Visitable> visitables) {
                        Log.d(TAG, "onNext: "+visitables.size());
                        if (!visitables.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+visitables.size());

                            getPresentationModel().visitableList.addAll(visitables);
                            loadDataDisplay();

                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadDataDisplay() {
        if(getMvpView()==null)return;
        getMvpView().loadDataDisplay();
    }
}
