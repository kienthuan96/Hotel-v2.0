package com.example.thuan.vdt2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Phone extends ArrayAdapter {
    private Context context;
    private int layout;
    private ArrayList<Phone> arrayList;
    public Adapter_Phone(@NonNull Context context, int resource, @NonNull ArrayList<Phone> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,parent, false);

        TextView txtTen=convertView.findViewById(R.id.txtTen);
        TextView txtGia=convertView.findViewById(R.id.txtGia);

        Phone phone=new Phone(arrayList.get(position));
        txtTen.setText(phone.getTenDT());
        txtGia.setText(phone.getgiaDT());
        return convertView;
    }
}
