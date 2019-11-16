package com.example.mt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mt.dataModel.Accounts;
import com.example.mt.dataModel.Games;

import java.util.ArrayList;

public class AdapterActivity extends ArrayAdapter<Games> {

    private ArrayList<Games> dataset;
    private Context context;

    private static class ViewHolder{
        TextView tvName;
        TextView tvRate;
        TextView tvPrice;
    }

    public AdapterActivity(ArrayList<Games> data, Context context){

        super (context,R.layout.activity_adapter,data);
        this.dataset = data;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Games game = getItem(position);

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_adapter,parent,false);

            holder.tvName = convertView.findViewById(R.id.tv_Name);
            holder.tvRate = convertView.findViewById(R.id.tv_Rate);
            holder.tvPrice = convertView.findViewById(R.id.tv_Price);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvName.setText(game.getGameName());
        holder.tvRate.setText(game.getGameRate());
        holder.tvPrice.setText(game.getGamePrice());

        return convertView;
    }
}
