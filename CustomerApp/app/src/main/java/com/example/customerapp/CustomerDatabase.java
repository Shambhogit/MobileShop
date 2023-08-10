package com.example.customerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.util.ArrayList;

public class CustomerDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_name";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "Customer_details";
    private static final String CST_ID = "id";
    private static final String CST_NAME = "name";
    private static final String CST_NUMBER = "number";
    private static final String CST_ADDRESS = "address";
    private static final String CST_PIN_CODE = "pin_code";

    public CustomerDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE "+TABLE_NAME+"("
                +CST_ID+" TEXT,"
                +CST_NAME+" TEXT,"
                +CST_NUMBER+" TEXT,"
                +CST_ADDRESS+" TEXT,"
                +CST_PIN_CODE+ " TEXT"+")";

        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version){

    }

    public void addCustomerData(CustomerModule customer){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CST_ID,customer.id);
        values.put(CST_NAME,customer.name);
        values.put(CST_NUMBER,customer.number);
        values.put(CST_ADDRESS,customer.address);
        values.put(CST_PIN_CODE,customer.pinCode);

        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<CustomerModule> getAllData(){
        ArrayList<CustomerModule> data = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor custData = db.rawQuery(query,null);
        while (custData.moveToNext()){
            data.add(new CustomerModule(custData.getString(0),custData.getString(1),custData.getString(2),custData.getString(3),custData.getString(4)));
        }
        custData.close();

        return data;
    }
}
