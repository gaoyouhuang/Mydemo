package com.example.scs.myapplication.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

/**
 * Created by scs on 17-9-21.
 */

public abstract class BaseFragment extends Fragment implements LoaderManager.LoaderCallbacks {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



}
