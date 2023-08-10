package com.example.signinpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LoginDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LOGIN_DETAILS";

    private static final String TABLE_NAME = "LOGIN_DATA";
    private static final String INFO_TABLE_NAME = "USER_DATA";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String EMAIL_ID = "email";
    private static final String HOBBY = "hobby";



    public LoginDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+"("+USER_NAME+" TEXT, "+PASSWORD+" TEXT"+")";
        String query1 = "CREATE TABLE "+INFO_TABLE_NAME+"("
                +EMAIL_ID+" TEXT,"
                +USER_NAME+" TEXT, "
                +PASSWORD+" TEXT,"
                +HOBBY+" TEXT "+")";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME,userName);
        values.put(PASSWORD,password);

        db.insert(TABLE_NAME,null,values);
    }

    public void insertUserData(String email, String userName, String password, String hobby){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMAIL_ID, email);
        values.put(USER_NAME,userName);
        values.put(PASSWORD,password);
        values.put(HOBBY,hobby);
        db.insert(INFO_TABLE_NAME,null, values);
    }

    public ArrayList<UserModel> checkUserNamePassword(String userName, String password){
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+USER_NAME+"='"+userName+"'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery(query,null);
        ArrayList<UserModel> idPass = new ArrayList<>();
        while (data.moveToNext()){
            idPass.add(new UserModel(data.getString(0),data.getString(1)));
        }

        data.close();

        return  idPass;
    }

    public StringBuilder getHobby(String userName){
        StringBuilder hobby = new StringBuilder();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT "+HOBBY+" FROM "+INFO_TABLE_NAME+" WHERE "+USER_NAME+"='"+userName+"'";

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            hobby = hobby.append(cursor.getString(0));
        }
        cursor.close();

        return hobby;
    }

    public StringBuilder getPassword(String userName){
        StringBuilder password = new StringBuilder();
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT "+PASSWORD+" FROM "+TABLE_NAME+" WHERE "+USER_NAME+"='"+userName+"'",null);
        while (data.moveToNext()){
            password.append(data.getString(0));
        }
        data.close();
        return password;
    }
}
