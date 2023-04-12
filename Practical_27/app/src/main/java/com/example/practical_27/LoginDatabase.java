package com.example.practical_27;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LoginDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "logins";
    private static final int DATABASE_ID = 1;
    private static final String TABLE_NAME = "login_ids";
    private static final String USER_NAME = "user_name";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_ID = "user_id";
    private static final String USER_PASSWORD = "user_password";

    public LoginDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table login_ids (user_name text, user_phone text, user_id text, user_password text);
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+USER_NAME+" TEXT,"+USER_PHONE+" TEXT,"+USER_ID+" TEXT,"+USER_PASSWORD+" TEXT "+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addLoginId(String name, String phone, String loginId, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,name);
        values.put(USER_PHONE,phone);
        values.put(USER_ID,loginId);
        values.put(USER_PASSWORD,password);

        db.insert(TABLE_NAME,null,values);
    }

    public String getIdPass(String userId){
        SQLiteDatabase db = getWritableDatabase();
        String password = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        while (cursor.moveToNext()) {
            if (cursor.getString(2).equals(userId)) {
                password = cursor.getString(3);
            }
        }
        return password;
    }
}
