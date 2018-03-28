package com.example.scs.myapplication.lazyfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by scs on 17-10-10.
 */

public abstract class BaseLazyFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(getRunningActivityName() + " onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(getRunningActivityName() + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        System.out.println(getRunningActivityName() + " onCreateView");
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(getRunningActivityName() + " onViewCreated");
        configViews();
    }

    protected abstract void configViews();

    protected abstract int getLayoutId();


    @Override
    public void onStart() {
        super.onStart();
        System.out.println(getRunningActivityName() + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(getRunningActivityName() + " onResume");
    }



    private String getRunningActivityName() {
        if (getContext() == null)
            return "";
        String contextString = getContext().toString();
        return contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
    }


    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println(getRunningActivityName() + " onActivityCreated");
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        System.out.println(getRunningActivityName() + " setUserVisibleHint " + isVisibleToUser);
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }
}
