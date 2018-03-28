package com.example.scs.myapplication.mvp.factory;

import com.example.scs.myapplication.mvp.base.BasePresenter;

/**
 * Created by ideal-gn on 2017/6/29.
 */

public interface PresenterFactory<P extends BasePresenter> {

    P create();
}
