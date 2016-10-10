package com.longngo.footballfan.ui.viewmodel;

import com.longngo.footballfan.ui.adapter.vmfactory.VMTypeFactory;

import java.util.List;

/**
 * Created by Long on 10/10/2016.
 */

public class SectionVM extends BaseVM{
    String title;

    public List<BaseVM> getBaseVMs() {
        return baseVMs;
    }

    public void setBaseVMs(List<BaseVM> baseVMs) {
        this.baseVMs = baseVMs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    List<BaseVM> baseVMs;

    @Override
    public int getVMType(VMTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
