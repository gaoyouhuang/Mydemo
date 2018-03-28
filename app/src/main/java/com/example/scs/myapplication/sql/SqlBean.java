package com.example.scs.myapplication.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scs on 18-3-17.
 */

public class SqlBean {

    public static final String TABLE_NAME = "friend";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_NAME_ID = "username";
    /**
     * primary key:主键
     * autoincrement：自增型变量
     * if not exists ：如果创建的表存在就不在创建
     */
    public static final String USERNAME_TABLE_CREATOR = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT, "//id
            + COLUMN_NAME_ID + " text );";//username

    private DbOpenHelper dbOpenHelper;

    private SqlBean() {
        if (dbOpenHelper == null)
            dbOpenHelper = DbOpenHelper.getInstance();
    }

    private static SqlBean sqlBean;

    public static synchronized SqlBean getInstance() {
        if (sqlBean == null)
            sqlBean = new SqlBean();
        return sqlBean;
    }


    /**
     * 添加 or 更新
     *
     * @param addBean
     * @return
     */

    public synchronized long saveBean(Bean addBean) {
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            ContentValues values = new ContentValues();
//            values.put(COLUMN_ID, addBean.getId());
            values.put(COLUMN_NAME_ID, addBean.getName());
            int update = database.update(TABLE_NAME, values, COLUMN_NAME_ID + "=?", new String[]{addBean.getName()});
            if (update == 0) {
                int insert = (int) database.insert(TABLE_NAME, null, values);
                database.close();
                return insert;
            } else {
                database.close();
                return update;
            }
        }
        database.close();

        return -1;
    }

    /**
     * 获取指定数据
     *
     * @param name
     * @return
     */
    public synchronized Bean seachBean(String name) {
        SQLiteDatabase database = dbOpenHelper.getReadable();
        Bean bean = new Bean();
        if (database.isOpen()) {
            String sql = "select * from " + TABLE_NAME + " where " + COLUMN_NAME_ID + "=?";
            Cursor cursor = database.rawQuery(sql, new String[]{name});
            while (cursor.moveToNext()) {
                bean.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                bean.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID)));
            }
        }
        database.close();

        return bean;
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    public synchronized List<Bean> getListBean() {
        List<Bean> list = new ArrayList<>();
        SQLiteDatabase database = dbOpenHelper.getReadable();
        if (database.isOpen()) {
            String sql = "select * from " + TABLE_NAME;
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Bean bean = new Bean();
                bean.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                bean.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID)));
                list.add(bean);
            }
        }
        database.close();
        return list;
    }

    public synchronized void deleBean(String name) {
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            int delete = database.delete(TABLE_NAME, COLUMN_NAME_ID + "=?", new String[]{name});
        }
        database.close();

    }

    /**
     * 删除表中的数据，但保留表结构
     * 退出程序时执行此操作，确保切换用户不会出现数据混乱
     */
    public void deleteAll() {
        SQLiteDatabase db = dbOpenHelper.getWritable();
        if (db.isOpen()) {
            int delete = db.delete(TABLE_NAME, null, null);
        }
        db.close();
    }

}
