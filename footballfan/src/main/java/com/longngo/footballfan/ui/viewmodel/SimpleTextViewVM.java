package com.longngo.footballfan.ui.viewmodel;

import com.longngo.footballfan.ui.adapter.vmfactory.VMTypeFactory;

/**
 * Created by Long on 10/10/2016.
 */

public class SimpleTextViewVM extends BaseVM {
    String des;

    public SimpleTextViewVM(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public int getVMType(VMTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
