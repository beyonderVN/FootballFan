package com.longngo.footballfan.ui.di;

import android.content.Context;

import com.longngo.footballfan.data.footballapi.FootballService;
import com.longngo.footballfan.data.footballapi.FootballServiceApi;
import com.longngo.footballfan.data.source.CompetitionsDataSource;
import com.longngo.footballfan.data.source.CompetitionsRepository;
import com.longngo.footballfan.data.source.local.CompetitionsLocalDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;
import com.longngo.footballfan.ui.activity.MainActivity;
import com.longngo.footballfan.util.schedulers.BaseSchedulerProvider;

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



}
