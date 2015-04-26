package com.bathust.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by bathust on 15-4-16.
 */
public class UserInfoDbHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERINFO = "create table userInfo(" +
            "id integer primary key autoincrement" +
            "name text" +
            "weibo text)";
    private Context mContext;
    public UserInfoDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USERINFO);
        Toast.makeText(mContext,"Created succeed",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
