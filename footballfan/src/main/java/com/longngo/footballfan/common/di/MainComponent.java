package com.longngo.footballfan.common.di;

import android.content.Context;

import com.longngo.footballfan.data.backend.footballapi.FootballService;
import com.longngo.footballfan.data.backend.footballapi.FootballServiceApi;
import com.longngo.footballfan.data.source.CompetitionsRepository;
import com.longngo.footballfan.ui.activity.competiondetail.CompetionDetailActivity;
import com.longngo.footballfan.ui.activity.competions.MainActivity;
import com.longngo.footballfan.common.schedulers.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    Context context();
    FootballService dataManager();
    FootballServiceApi footballService();
    BaseSchedulerProvider schedulerProvider() ;
    CompetitionsRepository competitionsRepository();
    void inject(MainActivity mainActivity);

    void inject(CompetionDetailActivity competionActivity);



}
