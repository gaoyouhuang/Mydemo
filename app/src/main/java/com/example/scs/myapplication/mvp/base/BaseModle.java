package com.example.scs.myapplication.mvp.base;

/**
 * Created by scs on 17-9-21.
 */

public interface BaseModle {
    interface OKHttpTypeListener {
        void Success(String successdata);

        void Error(String errordata);
    }
}
