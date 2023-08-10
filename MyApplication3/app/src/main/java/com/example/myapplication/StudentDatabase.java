package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "student_database";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "student_data";
    private static final String STUDETN_ID = "student_id";
    private static final String STUDENT_NAME = "student_name";
    private static final String STUDENT_NUMBER = "student_number";
    private static final String STUDENT_ADDRESS = "student_address";
    private static final String STUDENT_PIN = "student_pin";

    public StudentDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE "+TABLE_NAME+"("
                +STUDETN_ID+" TEXT,"
                +STUDENT_NAME+" TEXT,"
                +STUDENT_NUMBER+" TEXT,"
                +STUDENT_ADDRESS+" TEXT,"
                +STUDENT_PIN+" TEXT"+")";

        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int old_version,int new_version){
        db.execSQL("DROP TABLE "+ TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(String id, String name, String number, String address, String pin){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(STUDETN_ID,id);
        values.put(STUDENT_NAME,name);
        values.put(STUDENT_NUMBER,number);
        values.put(STUDENT_ADDRESS,address);
        values.put(STUDENT_PIN,pin);

        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<String> getNames(){

        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("SELECT "+STUDENT_NAME+" FROM "+TABLE_NAME,null);

        while (data.moveToNext()){
            names.add(data.getString(0));
        }

        data.close();
        return names;
    }
}
