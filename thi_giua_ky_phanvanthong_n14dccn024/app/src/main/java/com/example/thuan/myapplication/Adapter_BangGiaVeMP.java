package com.example.thuan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_BangGiaVeMP extends BaseAdapter {
    //Khai báo mảng và biến Context để kết nói
   private  Context context;
   private ArrayList<class_BangGiaVeXeMienPhi> list;

    public Adapter_BangGiaVeMP(Context context, ArrayList<class_BangGiaVeXeMienPhi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Khai báo layout Inflat tẻ để ánh xạ
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View  rowMP = inflater.inflate(R.layout.layout_item_banggiavemienphi,null);


        //Ánh xa id vào itemTe
       TextView _IDMP = (TextView) rowMP.findViewById(R.id.txtIDPM);
        TextView _tenMP = (TextView) rowMP.findViewById(R.id.txtTenMP);
        TextView _moTaMP =  (TextView) rowMP.findViewById(R.id.motaMP);


        //Lấy vị trí trong danh sách
        class_BangGiaVeXeMienPhi BGXMP = list.get(position);
        _IDMP.setText(String.valueOf(BGXMP.iDMP));
        _tenMP.setText(BGXMP.tenMP);
        _moTaMP.setText(BGXMP.moTaMP);


        return rowMP;

    }
}
