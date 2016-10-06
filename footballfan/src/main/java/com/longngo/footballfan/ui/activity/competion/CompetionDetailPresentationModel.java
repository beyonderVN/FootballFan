package com.longngo.footballfan.ui.activity.competion;

import com.longngo.footballfan.ui.adapter.vmfactory.Visitable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 06/10/2016.
 */

public class CompetionDetailPresentationModel implements Serializable {
    public List<Visitable> visitableList ;

    public CompetionDetailPresentationModel() {
        this.visitableList = new ArrayList<>();
    }
}
