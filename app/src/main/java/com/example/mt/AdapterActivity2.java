package com.example.mt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mt.dataModel.Accounts;

import java.util.ArrayList;

public class AdapterActivity2 extends ArrayAdapter<Accounts> {

    private ArrayList<Accounts> dataset;
    private Context context;

    private static class ViewHolder{
        EditText username;
        EditText password;
    }

    public AdapterActivity2(ArrayList<Accounts> data, Context context){

        super (context,R.layout.activity_adapter,data);
        this.dataset = data;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Accounts accounts = getItem(position);

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main,parent,false);

            holder.username = convertView.findViewById(R.id.UserName);
            holder.password = convertView.findViewById(R.id.Password);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.username.setText(accounts.getUsername());
        holder.password.setText(accounts.getPassword());

        return convertView;
    }
}
