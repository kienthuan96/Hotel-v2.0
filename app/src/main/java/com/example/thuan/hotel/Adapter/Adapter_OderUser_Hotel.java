package com.example.thuan.hotel.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuan.hotel.Model.Oder;
import com.example.thuan.hotel.Model.OrderUser;
import com.example.thuan.hotel.R;

import java.util.List;

public class Adapter_OderUser_Hotel extends BaseAdapter {
    private Context mContext;
    private List<OrderUser> mOrderuslist;

    public Adapter_OderUser_Hotel (Context mContext, List<OrderUser> mOrderuslist) {
        this.mContext = mContext;
        this.mOrderuslist = mOrderuslist;
    }

    @Override
    public int getCount() {
        return mOrderuslist.size();
    }

    @Override
    public Object getItem(int i) {
        return mOrderuslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.listoderuser,null);
        TextView tvTenKhacSan = (TextView)v.findViewById(R.id.txtTKS);
        TextView tvDiaChi = (TextView)v.findViewById(R.id.txtdiaC);
        TextView tvDateStarOder = (TextView)v.findViewById(R.id.txtNgayD);
        TextView tvDateEndOder = (TextView)v.findViewById(R.id.txtNgayT);
        TextView RoomOder = (TextView)v.findViewById(R.id.txtsoPhongD);
        TextView Totalmoney = (TextView)v.findViewById(R.id.txttongT);

        tvTenKhacSan.setText(mOrderuslist.get(i).getNamehotel());
        tvDiaChi.setText(mOrderuslist.get(i).getAddressHotel());
        tvDateStarOder.setText(mOrderuslist.get(i).getDateStartOrder());
        tvDateEndOder.setText(mOrderuslist.get(i).getDateEndOrder());
        RoomOder.setText(mOrderuslist.get(i).getRoomOrder()+"");
        Totalmoney.setText(mOrderuslist.get(i).getTotalMoney()+"");

        return v;

    }
}