package com.example.hp.sinhvien;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VINHHUNG-PC on 4/15/2018.
 */

public class Adapte extends ArrayAdapter{

    Context context;
    int layout;
    ArrayList<CongNhan> list;

    public Adapte(@NonNull Context context, int resource, @NonNull ArrayList<CongNhan> objects) {
        super(context, resource,  objects);
        this.context = context;
        this.layout = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,null);

        TextView id = convertView.findViewById(R.id.tvID);
        TextView hoten = convertView.findViewById(R.id.tvHoTen);

        CongNhan sinhvien = list.get(position);

        String tam = String.valueOf(sinhvien.getId());
        id.setText(tam);
        hoten.setText(sinhvien.getHoTen());



        return convertView;
    }
}
