package com.example.qestion_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListDataAdapter extends ArrayAdapter<EmployeeModal> {

    Context context;
    ArrayList<EmployeeModal> employeeModals;

    public ListDataAdapter(@NonNull Context context, ArrayList<EmployeeModal> employeeModals) {
        super(context,0, employeeModals);
        this.context = context;
        this.employeeModals = employeeModals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.custome_list_view_item,parent,false);
        }

        TextView id = itemView.findViewById(R.id.tvID);
        TextView name = itemView.findViewById(R.id.tvName);
        TextView phone = itemView.findViewById(R.id.tvPhone);
        TextView salary = itemView.findViewById(R.id.tvSalary);
        TextView joining = itemView.findViewById(R.id.tvJoining);

        id.setText(employeeModals.get(position).id);
        name.setText(employeeModals.get(position).name);
        phone.setText(employeeModals.get(position).phone);
        salary.setText(employeeModals.get(position).salary);
        joining.setText(employeeModals.get(position).joining);

        return itemView;
    }
}
