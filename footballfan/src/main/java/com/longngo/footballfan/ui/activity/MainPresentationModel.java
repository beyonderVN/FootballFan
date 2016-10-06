package com.longngo.footballfan.ui.activity;

import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 06/10/2016.
 */

public class MainPresentationModel implements Serializable {
    public List<Visitable> visitableList ;

    public MainPresentationModel() {
        this.visitableList = new ArrayList<>();
    }
}
