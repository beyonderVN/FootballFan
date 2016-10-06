package com.longngo.footballfan.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.data.source.CompetitionsDataSource;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.util.schedulers.BaseSchedulerProvider;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Long on 10/6/2016.
 */
@Singleton
public class CompetitionsLocalDataSource implements CompetitionsDataSource {
    @Nullable
    private static CompetitionsLocalDataSource INSTANCE;

    @NonNull
    private final BriteDatabase mDatabaseHelper;

    @NonNull
    private Func1<Cursor, Competition> mCompetitionMapperFunction;

    // Prevent direct instantiation.
    private CompetitionsLocalDataSource() {
        checkNotNull(FootballFanApplication.getMainComponent().context(), "context cannot be null");
        checkNotNull(FootballFanApplication.getMainComponent().schedulerProvider(), "scheduleProvider cannot be null");
        CompetitionsDbHelper dbHelper = new CompetitionsDbHelper(FootballFanApplication.getMainComponent().context());
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, FootballFanApplication.getMainComponent().schedulerProvider().io());
        mCompetitionMapperFunction = new Func1<Cursor, Competition>() {
            @Override
            public Competition call(Cursor c) {
                String itemId = c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_ENTRY_ID));

                String numberOfGames =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_G));
                String numberOfTeams =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_T));
                String lastUpdated =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_L_UPDATED));
                String year =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_YEAR));
                String caption =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_CAPTION));
                String league =
                        c.getString(c.getColumnIndexOrThrow(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_LEAGUE));
                return new Competition(itemId, numberOfGames, numberOfTeams, lastUpdated,
                        year,caption,league);
            }
        };
    }

    public static CompetitionsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionsLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Competition>> getCompetitions() {
        String[] projection = {
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_ENTRY_ID,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_G,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_T,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_L_UPDATED,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_YEAR,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_CAPTION,
                CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_LEAGUE
        };
        String sql = String.format("SELECT %s FROM %s", TextUtils.join(",", projection), CompetitionsPersistenceContract.CompetitionEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(CompetitionsPersistenceContract.CompetitionEntry.TABLE_NAME, sql)
                .mapToList(mCompetitionMapperFunction);
    }

    @Override
    public void saveCompetition(@NonNull Competition competition) {
        checkNotNull(competition);
        ContentValues values = new ContentValues();
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_ENTRY_ID, competition.getId());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_G, competition.getNumberOfGames());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_T, competition.getNumberOfTeams());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_L_UPDATED, competition.getLastUpdated());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_YEAR, competition.getYear());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_CAPTION, competition.getCaption());
        values.put(CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_LEAGUE, competition.getLeague());
        mDatabaseHelper.insert(CompetitionsPersistenceContract.CompetitionEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

}
