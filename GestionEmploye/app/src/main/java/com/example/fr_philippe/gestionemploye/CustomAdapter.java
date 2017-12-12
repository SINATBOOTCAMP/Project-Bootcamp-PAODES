package com.example.fr_philippe.gestionemploye;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by FR-PHILIPPE on 12/7/2017.
 */

public class CustomAdapter extends ArrayAdapter<DataItem> {

    Context context;
    int layoutResourceId;
    List<DataItem> data;

    public CustomAdapter(Context context, int resource, List<DataItem> objects){

        super(context,resource,objects);

        this.layoutResourceId=resource;
        this.context=context;
        this.data=objects;
    }

    static class DataHolder{
        ImageView ivIcon;
        TextView textProfil;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder=null;
        if(convertView==null){

            LayoutInflater inflater=((Activity)context).getLayoutInflater();

            convertView=inflater.inflate(layoutResourceId,null);

            holder=new DataHolder();
            holder.ivIcon=(ImageView)convertView.findViewById(R.id.ivIcon);
            holder.textProfil=(TextView)convertView.findViewById(R.id.etProfil);

            convertView.setTag(holder);
        }
        else {
            holder=(DataHolder)convertView.getTag();
        }
        DataItem dataItem=data.get(position);
        holder.textProfil.setText(dataItem.profilText);
        holder.ivIcon.setImageResource(dataItem.resIdThumbnail);

        return convertView;
    }
}
