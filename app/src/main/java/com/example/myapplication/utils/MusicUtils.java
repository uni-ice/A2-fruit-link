package com.example.myapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.sql.MusicBean;
import com.example.myapplication.sql.SqlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取手机上全部音乐
 */
public class MusicUtils {
    private SqlUtils sql;

    public MusicUtils(Context context) {
        sql = new SqlUtils(context);
    }

    //插入数据
    public void add(MusicBean bean) {
        SQLiteDatabase writableDatabase = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", bean.getName());
        values.put("path", bean.getPath());
        writableDatabase.insert("music", null, values);
    }

    //获取全部数据
    public List<MusicBean> getAll() {
        List<MusicBean> list = new ArrayList<>();
        SQLiteDatabase readableDatabase = sql.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from music", new String[]{});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String path = cursor.getString(cursor.getColumnIndex("path"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(new MusicBean(id, -1, path, name));
        }
        cursor.close();
        readableDatabase.close();
        return list;
    }


    //判断数据库是否为空
    public boolean isExists() {
        SQLiteDatabase readableDatabase = sql.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from music", new String[]{});
        boolean b = cursor.moveToFirst();
        cursor.close();
        readableDatabase.close();
        return b;
    }

}
