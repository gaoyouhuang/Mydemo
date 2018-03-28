package com.example.scs.myapplication.mvp;

import android.content.Context;
import android.content.Loader;

import com.example.scs.myapplication.mvp.base.BasePresenter;
import com.example.scs.myapplication.mvp.factory.PresenterFactory;

/**
 * Created by ideal-gn on 2017/6/29.
 */

public class PresenterLoader<P extends BasePresenter> extends Loader {

    private P prensenter;
    private PresenterFactory<P> factory;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public PresenterLoader(Context context, PresenterFactory<P> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (prensenter!=null){
            deliverResult(prensenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        prensenter = factory.create();
        deliverResult(prensenter);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        if (prensenter!=null) {
            prensenter.detach();
            prensenter = null;
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        if (prensenter!=null) {
            prensenter.detach();
            prensenter = null;
        }
    }


}
