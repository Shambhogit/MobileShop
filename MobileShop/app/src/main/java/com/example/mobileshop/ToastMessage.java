package com.example.mobileshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastMessage {
    private Context context;
    private View warning;
    private View error;
    private View info;
    private View success;

    public ToastMessage(Context context, View warning, View error, View info, View success) {
        this.context = context;
        this.warning = warning;
        this.error = error;
        this.info = info;
        this.success = success;
    }


    public void showInfoToastMessage(String message){
        Toast toast = new Toast(context);
        TextView tvMessage = info.findViewById(R.id.tvInfo);
        tvMessage.setText(message);
        toast.setView(info);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public void showErrorToastMessage(String message){
        Toast toast = new Toast(context);
        TextView tvMessage = error.findViewById(R.id.tvInfo);
        tvMessage.setText(message);
        toast.setView(error);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public void showWarningToastMessage(String message){
        Toast toast = new Toast(context);
        TextView tvMessage = warning.findViewById(R.id.tvInfo);
        tvMessage.setText(message);
        toast.setView(warning);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public void showSuccessToastMessage(String message){
        Toast toast = new Toast(context);
        TextView tvMessage = success.findViewById(R.id.tvInfo);
        tvMessage.setText(message);
        toast.setView(success);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
