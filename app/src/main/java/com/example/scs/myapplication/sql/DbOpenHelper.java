package com.example.scs.myapplication.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.scs.myapplication.MyApplication;

/**
 * Created by scs on 18-3-17.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mysql";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase mDatabase;
    private static volatile DbOpenHelper dbOpenHelper;

    private DbOpenHelper() {
        super(MyApplication.getInstance(), DB_NAME, null, DB_VERSION);
    }

    public static DbOpenHelper getInstance() {
        if (dbOpenHelper == null) {
            synchronized (DbOpenHelper.class) {
                if (dbOpenHelper == null) {
                    dbOpenHelper = new DbOpenHelper();
                }
            }
        }
        return dbOpenHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlBean.USERNAME_TABLE_CREATOR);
    }

    public synchronized SQLiteDatabase getWritable() {
        mDatabase = dbOpenHelper.getWritableDatabase();
        return mDatabase;
    }

    public synchronized SQLiteDatabase getReadable() {
        mDatabase = dbOpenHelper.getReadableDatabase();
        return mDatabase;
    }

    public synchronized void closeDB() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
