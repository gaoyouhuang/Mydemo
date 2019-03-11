package com.example.scs.myapplication.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SqlStudent {
    private static final String TABLE_NAME = "student";
    private static final String SQL_ID = "id";
    private static final String SQL_NAME = "name";
    private static final String SQL_AGE = "age";
    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + "(" + SQL_ID + " integer PRIMARY KEY AUTOINCREMENT," + SQL_NAME + " text," + SQL_AGE + " integer)";

    private DbOpenHelper dbOpenHelper;

    private SqlStudent() {
        if (dbOpenHelper == null) {
            dbOpenHelper = DbOpenHelper.getInstance();
        }
    }

    private static SqlStudent sqlStudent;

    public static SqlStudent getInstance() {
        if (sqlStudent == null) {
            synchronized (SqlStudent.class) {
                if (sqlStudent == null) {
                    sqlStudent = new SqlStudent();
                }
            }
        }
        return sqlStudent;
    }

    public void add(ArrayList<Student> list) {
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            database.beginTransaction();
            for (Student s : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SQL_NAME, s.getName());
                contentValues.put(SQL_AGE, s.getAge());
                database.insert(TABLE_NAME, null, contentValues);
            }
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        database.close();
    }

    public void update(String name, Student student) {
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQL_NAME, student.getName());
            contentValues.put(SQL_AGE, student.getAge());
            database.update(TABLE_NAME, contentValues, SQL_NAME + "=?", new String[]{name});
        }
        database.close();
    }

    public void dele(String name, boolean all_dele) {
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            if (all_dele) {
                database.delete(TABLE_NAME, null, null);
            } else {
                database.delete(TABLE_NAME, SQL_NAME + "=?", new String[]{name});
            }
        }
    }

    public ArrayList<Student> seach(String name, boolean all_seach) {
        ArrayList<Student> list = new ArrayList<>();
        SQLiteDatabase database = dbOpenHelper.getWritable();
        if (database.isOpen()) {
            if (!all_seach) {
                String sql = "select * from " + TABLE_NAME + " where " + SQL_NAME + "=?";
                Cursor cursor = database.rawQuery(sql, new String[]{name});
                while (cursor.moveToNext()) {
                    Student student = new Student();
                    student.setName(cursor.getString(cursor.getColumnIndex(SQL_NAME)));
                    student.setAge(cursor.getInt(cursor.getColumnIndex(SQL_AGE)));
                    list.add(student);
                    Log.i(TAG, "student " + student.toString());
                }
            } else {
                Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    Student student = new Student();
                    student.setName(cursor.getString(cursor.getColumnIndex(SQL_NAME)));
                    student.setAge(cursor.getInt(cursor.getColumnIndex(SQL_AGE)));
                    list.add(student);
                    Log.i(TAG, "student " + student.toString());

                }
            }
        }
        Log.i(TAG, "list size " + list.size());
        return list;
    }

    private static final String TAG = "sqlstudent";
}
