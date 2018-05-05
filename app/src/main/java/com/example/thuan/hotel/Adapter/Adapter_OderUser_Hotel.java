package com.example.thuan.hotel.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuan.hotel.Model.Oder;
import com.example.thuan.hotel.Model.OrderUser;
import com.example.thuan.hotel.R;

import java.text.DecimalFormat;
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
        TextView tvTenKhacSan = v.findViewById(R.id.txtTKS);
        TextView tvDiaChi = v.findViewById(R.id.txtdiaC);
        TextView tvDateStarOder = v.findViewById(R.id.txtNgayD);
        TextView tvDateEndOder = v.findViewById(R.id.txtNgayT);
        TextView RoomOder = v.findViewById(R.id.txtsoPhongD);
        TextView Totalmoney = v.findViewById(R.id.txttongT);

        tvTenKhacSan.setText(mOrderuslist.get(i).getNamehotel());
        //tvTenKhacSan.setText("123");
        //Log.d("KhachSan", mOrderuslist.get(i).getNamehotel());
        tvDiaChi.setText(mOrderuslist.get(i).getAddressHotel());
        tvDateStarOder.setText(mOrderuslist.get(i).getDateStartOrder());
        tvDateEndOder.setText(mOrderuslist.get(i).getDateEndOrder());
        RoomOder.setText(mOrderuslist.get(i).getRoomOrder()+" phòng");
        DecimalFormat df = new DecimalFormat("###,###,###");
        Totalmoney.setText(df.format(mOrderuslist.get(i).getTotalMoney()) + " VND");

        return v;

    }
}