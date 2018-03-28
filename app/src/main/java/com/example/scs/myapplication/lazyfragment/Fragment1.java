package com.example.scs.myapplication.lazyfragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scs.myapplication.R;

/**
 * Created by scs on 17-10-10.
 */

public class Fragment1 extends Fragment {
    View view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void configViews() {
        view = getView().findViewById(R.id.view);
//        view.setBackgroundResource(R.color.colorPrimary);
//        view.setBackgroundColor(Color.parseColor("#000000"));
        view.setBackground(getActivity().getResources().getDrawable(R.drawable.leak_canary_icon));
//        Drawable drawable =getActivity().getResources().getDrawable(R.drawable.leak_canary_icon);
//        view.setBackground(drawable);
    }

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
        View rootView = inflater.inflate(R.layout.fragment_lazy, container, false);
        System.out.println(getRunningActivityName() + " onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(getRunningActivityName() + " onViewCreated");
        configViews();
    }

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
        return "Fragment1";
    }

    protected boolean isViewInitiated;//只有去初始化UI线程　才能去更改数据
    protected boolean isVisibleToUser;//表示加载数据
    protected boolean isDataInitiated;//表示是否只加载一次数据

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

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        System.out.println(getRunningActivityName() + " " + isVisibleToUser + " " + isViewInitiated + " " + isDataInitiated);
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    public void fetchData() {
        System.out.println(getRunningActivityName() + " fetchData");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println(getRunningActivityName() + " onHiddenChanged "+hidden);

    }
}
