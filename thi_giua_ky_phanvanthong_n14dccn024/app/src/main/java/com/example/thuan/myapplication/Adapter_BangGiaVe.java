package com.example.thuan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_BangGiaVe  extends BaseAdapter{
    //Khai báo mảng Bẩng Giá Vé và 1 biến Context
    Context context;
    ArrayList<class_BangGiaVeXe> list;
    //add 2  contructor :List , và context cho 2 biến vừa khởi tạo ở trên
    public Adapter_BangGiaVe(Context context, ArrayList<class_BangGiaVeXe> list) {
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
        // Khai báo Layout inFlater ddeeer truyền cái layout sử dụng biến context ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_item_banggiave,null);
       //Ánh xạ cái cái id vào item
        TextView _ID = (TextView) row.findViewById(R.id.idID);
        TextView _HinhThuc  = (TextView) row.findViewById(R.id.idHinhThuc);
        TextView _DoiTuong  = (TextView) row.findViewById(R.id.idDoiTuong);
        TextView _GiaVe  = (TextView) row.findViewById(R.id.idGiaVe);
        TextView _ChieuDai  = (TextView) row.findViewById(R.id.idChieuDai);


        //Lấy vị trí trong danh sách
        class_BangGiaVeXe BGX = list.get(position);
        _ID.setText(String.valueOf((BGX._iD)));
        _HinhThuc.setText(BGX._hinhThuc);
        _DoiTuong.setText(BGX._doiTuong);
        _GiaVe.setText(String.valueOf(BGX._giaVe));
        _ChieuDai.setText(String.valueOf(BGX._chieuDai));



        return row;





    }
}
