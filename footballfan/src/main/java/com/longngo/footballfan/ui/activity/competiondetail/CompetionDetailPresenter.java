package com.longngo.footballfan.ui.activity.competiondetail;

import android.util.Log;

import com.longngo.footballfan.common.coremvp.SimpleMVPPresenter;
import com.longngo.footballfan.common.schedulers.BaseSchedulerProvider;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.model.Fixture;
import com.longngo.footballfan.data.model.FixtureList;
import com.longngo.footballfan.data.model.LeagueTable;
import com.longngo.footballfan.data.model.Team;
import com.longngo.footballfan.data.source.TeamsRepository;
import com.longngo.footballfan.ui.viewmodel.BaseVM;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.viewmodel.SectionVM;
import com.longngo.footballfan.ui.viewmodel.SimpleTextViewVM;
import com.longngo.footballfan.ui.viewmodel.mapper.Mapper;

import java.util.ArrayList;
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
    void fetchRepositories(Competition competition){
        if (!getPresentationModel().isShouldFetchRepositories()) {
            return;
        }
        getPresentationModel().clearList();
        getPresentationModel().add(new CompetitionVM(competition));
        loadDataDisplay();
        getCompetitionDetail(Integer.valueOf(competition.getId()));
    }
    void getCompetitionDetail(int id){
        getTeamList(id);
//        getLeagueTable(id);
//        getFixtureList(id);

    }
    private void getTeamList(final int id) {
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
                        getLeagueTable(id);
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
                            SectionVM sectionVM = new SectionVM();
                            sectionVM.setTitle("Team List");
                            sectionVM.setBaseVMs(visitables);
                            getPresentationModel().add(sectionVM);
                            loadDataDisplay();

                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    private void getLeagueTable(final int id) {
        mSubscriptions.clear();
        Subscription subscription = teamsRepository
                .getLeagueTable(id)
                .map(new Func1<LeagueTable, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(LeagueTable teams) {
                        List<BaseVM>  baseVMs = new ArrayList<BaseVM>();
//                        for (Standing standing:teams.getStanding()
//                             ) {
//                            baseVMs.add(new SimpleTextViewVM(standing.getCrestURI()));
//                        }
                        return baseVMs;
                    }

                })

                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<BaseVM>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        getFixtureList(id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);

                    }

                    @Override
                    public void onNext(List<BaseVM> visitables) {
                        Log.d(TAG, "onNext: "+visitables.toString());

                    }
                });
        mSubscriptions.add(subscription);
    }
    private void getFixtureList(int id) {
        mSubscriptions.clear();
        Subscription subscription = teamsRepository
                .getFixtureList(id)
                .map(new Func1<FixtureList, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(FixtureList fixtureList) {
                        List<BaseVM>  baseVMs = new ArrayList<BaseVM>();
                        for (Fixture fixture:fixtureList.getFixtures()
                             ) {
                            baseVMs.add(new SimpleTextViewVM(fixture.getHomeTeamName()+" VS "+fixture.getAwayTeamName()));
                        }
                        return baseVMs;
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
                        Log.d(TAG, "onNext: "+visitables.toString());
                        SectionVM sectionVM = new SectionVM();
                        sectionVM.setTitle("Team List");
                        sectionVM.setBaseVMs(visitables);
                        getPresentationModel().add(sectionVM);
                        loadDataDisplay();
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
