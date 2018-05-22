package com.example.thuan.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IT on 1/5/2018.
 */

public class SqliteAdapter extends ArrayAdapter<Bus> {

    Context context;
    int layout;
    ArrayList<Bus> arrPerson;

    public SqliteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Bus> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        arrPerson = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Holder holder;
        LayoutInflater inflater = LayoutInflater.from(context);

        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
            holder = new Holder();
            holder.tvTen = convertView.findViewById(R.id.tvTen);
            holder.tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            holder.img = convertView.findViewById(R.id.imgv);
            holder.chb = convertView.findViewById(R.id.chbChosen);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        final Bus p = arrPerson.get(position);

        holder.tvTen.setText("Tên: " + p.getMaXe());
        holder.tvDiaChi.setText("Địa chỉ: " + p.getTenXe());
//        if(p.isTinhTrang()){
//            holder.img.setImageResource(R.drawable.ic_accessibility_24dp);
//        }else{
//            holder.img.setImageResource(R.drawable.ic_accessible_24dp);
//        }
        holder.chb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                p.setChosen(b);
//                Toast.makeText(getContext(),arrPerson.toString(),Toast.LENGTH_LONG).show();
            }
        });

        holder.chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    static class Holder{
        TextView tvTen, tvDiaChi;
        ImageView img;
        CheckBox chb;
    }
}
