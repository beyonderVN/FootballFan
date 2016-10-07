package com.longngo.footballfan.ui.activity.competiondetail;

import android.util.Log;

import com.longngo.footballfan.common.coremvp.SimpleMVPPresenter;
import com.longngo.footballfan.common.schedulers.BaseSchedulerProvider;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsRepository;
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

public class CompetionDetailPresenter extends SimpleMVPPresenter<CompetionDetailView,CompetionDetailPresentationModel> implements CompetionDetailView {
    private static final String TAG = "CompetionPresenter";
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    private TeamsRepository teamsRepository;
    @Inject
    CompetionDetailPresenter(BaseSchedulerProvider baseSchedulerProvider, TeamsRepository teamsRepository) {
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public void attachView(CompetionDetailView mvpView, CompetionDetailPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
    void fetchRepositories(int id){
        if (!getPresentationModel().isShouldFetchRepositories()) {
            return;
        }
        getCompetition(id);
    }
    private void getCompetition(int id) {
        mSubscriptions.clear();
        Subscription subscription = teamsRepository
                .getTeams(id)

                .map(new Func1<List<Team>, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(List<Team> teams) {
                        return Mapper.tranTeam(teams);
                    }
                })
                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<BaseVM>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(List<BaseVM> visitables) {
                        Log.d(TAG, "onNext: "+visitables.size());
                        if (!visitables.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+visitables.size());

                            getPresentationModel().add(visitables);
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
