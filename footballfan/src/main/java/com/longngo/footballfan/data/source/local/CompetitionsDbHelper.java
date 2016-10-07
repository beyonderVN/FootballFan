package com.longngo.footballfan.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Long on 10/6/2016.
 */

public class CompetitionsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "footballfan";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CompetitionsPersistenceContract.CompetitionEntry.TABLE_NAME + " (" +
                    CompetitionsPersistenceContract.CompetitionEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_G + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_N_O_T + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_L_UPDATED + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_CAPTION + TEXT_TYPE + COMMA_SEP +
                    CompetitionsPersistenceContract.CompetitionEntry.COLUMN_NAME_LEAGUE + TEXT_TYPE +
                    " )";

    public CompetitionsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
