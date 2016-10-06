package com.longngo.footballfan.ui.di;

import android.content.Context;

import com.longngo.footballfan.data.footballapi.FootballServiceApi;
import com.longngo.footballfan.data.footballapi.FootballServiceFactory;
import com.longngo.footballfan.data.source.CompetitionsDataSource;
import com.longngo.footballfan.data.source.remote.CompetitionRemoteDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.util.schedulers.BaseSchedulerProvider;
import com.longngo.footballfan.util.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private Context context;
    private final FootballFanApplication application;

    public MainModule(FootballFanApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    FootballServiceApi provideBourbonService() {
        return FootballServiceFactory.makeService();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider( SchedulerProvider schedulerProvider) {
        return schedulerProvider;
    }

    @Provides
    @Singleton
    CompetitionsDataSource provideCompetitionsDataSource(CompetitionRemoteDataSource competitionsDataSource) {
        return competitionsDataSource;
    }



}

