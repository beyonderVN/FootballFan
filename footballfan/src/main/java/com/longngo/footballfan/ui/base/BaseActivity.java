package com.longngo.footballfan.ui.base;


import android.support.annotation.NonNull;


import com.longngo.footballfan.coremvp.MVPActivity;
import com.longngo.footballfan.coremvp.MVPPresenter;
import com.longngo.footballfan.coremvp.MVPView;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public abstract class BaseActivity<M extends Serializable, V extends MVPView, P extends MVPPresenter<V, M>>
extends MVPActivity<M,V,P> {
    @Inject protected P presenter;

    @NonNull
    @Override
    protected P createPresenter() {
        return presenter;
    }
}