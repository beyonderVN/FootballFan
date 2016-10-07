package com.longngo.footballfan.ui.activity.competiondetail;

import com.longngo.footballfan.ui.activity.base.BasePresentationModel;
import com.longngo.footballfan.ui.viewmodel.BaseVM;

import java.util.List;

/**
 * Created by Admin on 06/10/2016.
 */

public class CompetionDetailPresentationModel extends BasePresentationModel<BaseVM> {

    public CompetionDetailPresentationModel() {
        super();
    }
    boolean shouldFetchRepositories = true;
    @Override
    public boolean isShouldFetchRepositories() {
        return shouldFetchRepositories;
    }
    public void add(BaseVM visitable){
        visitableList.add(visitable);
    }
    public void add(List<BaseVM> visitables){
        visitableList.addAll(visitables);
        shouldFetchRepositories=false;

    }
    public void clearList(){
        visitableList.clear();
    }
}
