package com.example.qestion_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EmployeeDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "emp_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "emp_data";
    private static final String EMP_ID = "emp_ID";
    private static final String EMP_NAME = "emp_NAME";
    private static final String EMP_PHONE = "emp_PHONE";
    private static final String EMP_SALARY = "emp_SALARY";
    private static final String EMP_JOINING = "emp_JOINING";

    public EmployeeDatabase (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE "+TABLE_NAME+"("
                +EMP_ID+" TEXT,"
                +EMP_NAME+" TEXT,"
                +EMP_PHONE+" TEXT,"
                +EMP_SALARY+" TEXT,"
                +EMP_JOINING+ " TEXT"+")";

        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version){
        String query = "DROP TABLE "+ TABLE_NAME ;
        db.execSQL(query);
        onCreate(db);
    }

    public void insertEmployee(EmployeeModal employeeModal){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMP_ID, employeeModal.id);
        values.put(EMP_NAME, employeeModal.name);
        values.put(EMP_PHONE, employeeModal.phone);
        values.put(EMP_SALARY, employeeModal.salary);
        values.put(EMP_JOINING, employeeModal.joining);

        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<EmployeeModal> getAllEmployee(){
        ArrayList<EmployeeModal> employeeModals = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);

        while(data.moveToNext()){
            employeeModals.add(new EmployeeModal(data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)));
        }

        data.close();
        return employeeModals;
    }
}
