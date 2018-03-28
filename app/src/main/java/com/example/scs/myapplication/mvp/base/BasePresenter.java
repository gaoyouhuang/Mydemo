package com.example.scs.myapplication.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by scs on 17-9-21.
 */

public  class BasePresenter<V extends BaseView> {
    protected WeakReference<V> view;

    public void attach(V view){
        this.view = new WeakReference<>(view);
    }

    public V getView(){
        if (view!=null)
            return view.get();
        return null;
    }

    public void detach(){
        if (view!=null)
            view.clear();
    }
}
