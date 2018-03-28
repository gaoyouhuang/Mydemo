package com.example.scs.myapplication.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by scs on 17-9-14.
 */

public class StudentBean extends BaseObservable {


//    public final ObservableField<String> name = new ObservableField<>();
//    public final ObservableInt old = new ObservableInt();
//    public final ObservableBoolean studemtis = new ObservableBoolean();

    private String name = "";
    private int old = 0;
    private boolean studentis = false;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.scs.myapplication.BR.name);
    }

    @Bindable
    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
        notifyPropertyChanged(com.example.scs.myapplication.BR.old);
    }

    @Bindable
    public boolean isStudentis() {
        return studentis;
    }

    public void setStudentis(boolean studentis) {
        this.studentis = studentis;
        notifyPropertyChanged(com.example.scs.myapplication.BR.studentis);
    }
}
