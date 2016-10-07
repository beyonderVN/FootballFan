package com.longngo.footballfan.ui.activity.competiondetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.FootballFanApplication;
import com.longngo.footballfan.ui.adapter.BaseAdapter;
import com.longngo.footballfan.ui.activity.base.BaseActivity;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetionDetailActivity extends BaseActivity<CompetionDetailPresentationModel,CompetionDetailView,CompetionDetailPresenter> implements CompetionDetailView {
    private static final String TAG = "CompetionDetailActivity";
    private static final String COMPETITION= "COMPETITION";

    @BindView(R.id.list)
    RecyclerView listRV;

    BaseAdapter baseAdapter;

    public static Intent createIntent(Context context, Competition competition){
        Intent intent = new Intent(context, CompetionDetailActivity.class);
        intent.putExtra(COMPETITION, competition);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        listRV.setLayoutManager( new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseAdapter = new BaseAdapter(presenter.getPresentationModel().getVisitableList());
        listRV.setAdapter(baseAdapter);

        Competition competition = (Competition) getIntent().getSerializableExtra(COMPETITION);
        presenter.getPresentationModel().add(new CompetitionVM(competition));
        presenter.fetchRepositories(Integer.valueOf(competition.getId()) );
    }

    @Override
    protected void performFieldInjection() {
        FootballFanApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected CompetionDetailPresentationModel createPresentationModel() {
        return new CompetionDetailPresentationModel();
    }


    @Override
    public void loadDataDisplay() {
        baseAdapter.notifyDataSetChanged();
    }
}
