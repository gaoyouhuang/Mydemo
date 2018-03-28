package com.example.scs.myapplication.mvp.base;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.scs.myapplication.mvp.PresenterLoader;
import com.example.scs.myapplication.mvp.factory.PresenterFactory;


/**
 * Created by abc on 2016/11/21.
 */

public abstract class AActivity<P extends BasePresenter, V extends BaseView> extends BaseActivity {
    public AActivity activity = this;
    public P presenter;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    PopupWindow popw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//让ActionBar消失
        setContentView(setlayout());
//        presenter = createPresenter();

        getLoaderManager().initLoader(getloaderid(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new PresenterLoader<P>(AActivity.this, new PresenterFactory<P>() {
                    @Override
                    public P create() {
                        return createPresenter();
                    }
                });
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P p) {
                AActivity.this.presenter = p;
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {

            }
        });
        init();
    }

    protected abstract P createPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
            presenter = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSoftInput();
    }

    public abstract int setlayout();

    public abstract int getloaderid();


    public void show_Toast(String st) {
        Toast.makeText(activity, st, Toast.LENGTH_SHORT).show();
    }

    public abstract void init();

    public void showSoftInput(final View view) {
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isShowKeyBoard) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    return;
                }
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null) {
                    isShowKeyBoard = manager.showSoftInput(view, 0);
                }
            }
        });
    }

    public void hideSoftInput() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                isShowKeyBoard = !manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    boolean isShowKeyBoard;


    /**
     * Show date picker popWindow
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showPopWin(View v, RelativeLayout rel, Handler hand, int type) {
        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);
        popw.showAsDropDown(rel, Gravity.TOP, 0, 0);
        trans.setDuration(400);
        trans.setInterpolator(new AccelerateDecelerateInterpolator());
//        root.startAnimation(trans);

    }

    /**
     * Dismiss date picker popWindow
     */
    public void dismissPopWin() {

        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateInterpolator());
        trans.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                popw.dismiss();
            }
        });

//        root.startAnimation(trans);
    }


}
