package com.example.tpjsonimage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
/*
public class Adapter extends ArrayAdapter {
    ArrayList<Livre> lv=null;
    private Activity context;

    public Adapter(Activity context, ArrayList<Livre>l) {
        super(context, R.layout.row_item);
        this.context = context;
        this.lv=l;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);

        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);


        textViewCapital.setText(lv.get(position).getNom());
        Picasso.get().load(lv.get(position).getImage()).into(imageFlag);

        return  row;
    }
}*/
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Livre> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView IdView;
        private ImageView but;

        public ViewHolder(View view) {
            super(view);
            IdView = (TextView) view.findViewById(R.id.textID);
            but=(ImageView) view.findViewById(R.id.imageID);
        }

        public TextView getIdView() {
            return IdView;
        }

        public ImageView getBut() {
            return but;
        }
    }
    public Adapter(List<Livre> dataSet) {
        localDataSet = dataSet;
    }

    public Adapter(List<Livre> localDataSet, RecyclerView layout) {
        this.localDataSet = localDataSet;
        layout.setAdapter(this);
    }


    public List<Livre> getLocalDataSet() {
        return localDataSet;
    }

    public void setLocalDataSet(List<Livre> localDataSet) {
        this.localDataSet = localDataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getIdView().setText(localDataSet.get(position).getNom());
        Picasso.get().load(localDataSet.get(position).getImage()).resize(50,50).into(holder.getBut());
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
