package com.longngo.footballfan.ui.activity.competions;

import com.longngo.footballfan.ui.activity.base.BasePresentationModel;
import com.longngo.footballfan.ui.viewmodel.BaseVM;

import java.util.List;

/**
 * Created by Admin on 06/10/2016.
 */

public class MainPresentationModel extends BasePresentationModel<BaseVM> {
    public MainPresentationModel() {
        super();
    }

    @Override
    public boolean isShouldFetchRepositories() {
        return visitableList==null||visitableList.size()==0;
    }
    public void add(BaseVM baseVM){
        visitableList.add(baseVM);
    }
    public void add(List<BaseVM> baseVMs){
        visitableList.addAll(baseVMs);
    }
}
