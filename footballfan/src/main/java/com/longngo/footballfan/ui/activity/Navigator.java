package com.longngo.footballfan.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.ui.activity.competion.CompetionDetailActivity;

/**
 * Created by Admin on 07/10/2016.
 */

public class Navigator {
    public static void navigateToCompetionDetailActivity(Context context, Competition competition) {
        if (context != null) {
            Intent intentToLaunch = CompetionDetailActivity.createIntent(context, competition);
            context.startActivity(intentToLaunch);
        }
    }
}
