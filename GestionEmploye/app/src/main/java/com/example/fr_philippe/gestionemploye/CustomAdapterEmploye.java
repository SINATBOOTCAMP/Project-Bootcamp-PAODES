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
 * Created by FR-PHILIPPE on 12/8/2017.
 */

public class CustomAdapterEmploye extends ArrayAdapter<DataItemEmploye> {

    Context context;
    int layoutResourceId;
    List<DataItemEmploye> data=null;

    public CustomAdapterEmploye(Context context,int resource,List<DataItemEmploye> objects){
        super(context,resource,objects);
        this.context=context;
        this.layoutResourceId=resource;
        this.data=objects;
    }

    static class DataHolderEmploye{
        ImageView ivProfilEmploye;
        TextView etNomEmploye;
        TextView etFonctionEmploye;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CustomAdapterEmploye.DataHolderEmploye holder=null;
        if(convertView==null){

            LayoutInflater inflater=((Activity)context).getLayoutInflater();

            convertView=inflater.inflate(layoutResourceId,null);

            holder=new DataHolderEmploye();
            holder.ivProfilEmploye=(ImageView)convertView.findViewById(R.id.ivProfilImage);
            holder.etNomEmploye=(TextView)convertView.findViewById(R.id.etNomEmploye);
            holder.etFonctionEmploye=(TextView)convertView.findViewById(R.id.etFonctionEmploye);

            convertView.setTag(holder);
        }
        else {
            holder=(CustomAdapterEmploye.DataHolderEmploye)convertView.getTag();
        }
        DataItemEmploye dataItem=data.get(position);
        holder.etNomEmploye.setText(dataItem.nameEmploye);
        holder.etFonctionEmploye.setText(dataItem.fonctionEmploye);
        holder.ivProfilEmploye.setImageResource(dataItem.resIdThumbnail);

        return convertView;
    }
}
