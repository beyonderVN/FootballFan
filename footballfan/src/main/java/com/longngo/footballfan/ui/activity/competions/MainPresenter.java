package com.longngo.footballfan.ui.activity.competions;

import android.util.Log;

import com.longngo.footballfan.common.coremvp.SimpleMVPPresenter;
import com.longngo.footballfan.common.schedulers.BaseSchedulerProvider;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.CompetitionsRepository;
import com.longngo.footballfan.ui.viewmodel.BaseVM;
import com.longngo.footballfan.ui.viewmodel.mapper.Mapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 06/10/2016.
 */

public class MainPresenter extends SimpleMVPPresenter<MainView,MainPresentationModel> implements MainView{
    private static final String TAG = "MainPresenter";
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    private CompetitionsRepository competitionsRepository;
    @Inject
    MainPresenter(BaseSchedulerProvider baseSchedulerProvider, CompetitionsRepository competitionsRepository) {
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.competitionsRepository = competitionsRepository;
    }

    @Override
    public void attachView(MainView mvpView, MainPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        fetchRepositories("2016");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    public void fetchRepositories(String season){
        if (!getPresentationModel().isShouldFetchRepositories()) {
            return;
        }
        getCompetitions(season);
    }
    private void getCompetitions(String season) {
        mSubscriptions.clear();
        Subscription subscription = competitionsRepository
                .getCompetitions()
                .map(new Func1<List<Competition>, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(List<Competition> competitions) {
                        return Mapper.tranCompetition(competitions);
                    }
                })
                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<BaseVM>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BaseVM> competitions) {
                        Log.d(TAG, "onNext: "+competitions.size());
                        if (!competitions.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+competitions.size());

                            getPresentationModel().add(competitions);
                            loadCompetitions();

                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadCompetitions() {
        if(getMvpView()==null)return;
        getMvpView().loadCompetitions();
    }
}
