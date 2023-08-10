package com.example.qestion_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    ListView lvData;
    ArrayList<EmployeeModal> employeeModals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        lvData = findViewById(R.id.lvData);
        EmployeeDatabase db = new EmployeeDatabase(ShowData.this);
        employeeModals = db.getAllEmployee();

        ListDataAdapter adapter = new ListDataAdapter(ShowData.this,employeeModals);

        lvData.setAdapter(adapter);
    }
}