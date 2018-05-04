package com.example.thuan.hotel.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuan.hotel.Activity.MainActivity;
import com.example.thuan.hotel.Activity.SuaGhiChu_Activity;
import com.example.thuan.hotel.Activity.ThemBinhLuanActivity;
import com.example.thuan.hotel.Helper.Database;
import com.example.thuan.hotel.Model.GhiChu;
import com.example.thuan.hotel.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class Adapter_GhiChu extends BaseAdapter {
    private   String DATABASE_NAME = "Hotel_NoteOffline.sqlite";
    private Activity context ;
private ArrayList<GhiChu> listGhiChu ;

    public Adapter_GhiChu(Activity context, ArrayList<GhiChu> listGhiChu) {
        this.context = context;
        this.listGhiChu = listGhiChu;
    }

    @Override
    public int getCount() {
        return listGhiChu.size();
    }

    @Override
    public Object getItem(int position) {
        return listGhiChu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
        View row = inflater.inflate(R.layout.layout_itemghichu,null);

        //Ánh Xạ
        ImageView _anhKhachSan =  (ImageView) row.findViewById(R.id.itemAnhKhachSan);
        TextView _tenGhiChu = (TextView) row.findViewById(R.id.itemTenGhiChu);
        TextView _tenKhachSan = (TextView) row.findViewById(R.id.itemTenKhachSan);
        TextView _ngayTaoGhiChu = (TextView) row.findViewById(R.id.itemNgayTaoGhiChu);
        TextView _diaChiKhachSan = (TextView) row.findViewById(R.id.itemDiaChiKhachSan);
        TextView _moTaKhachSan = (TextView) row.findViewById(R.id.itemMoTaKhachSan);
        Button _btnSuaGhiChu = (Button) row.findViewById(R.id.btnSuaGhiChu);
        Button _btnXoaGhiChu = (Button) row.findViewById(R.id.btnXoaGhiChu);

       GhiChu ghichu =  (GhiChu) listGhiChu.get(position);
        String ID;
        ID=String.valueOf( ghichu.idGhiChu);
        int ID1 = Integer.parseInt(ID);
        _tenGhiChu.setText(ghichu.tenGhiChu);
        _tenKhachSan.setText(ghichu.tenKSGhiChu);
        _ngayTaoGhiChu.setText(String.valueOf(ghichu.ngayTaoGhiChu));
        _diaChiKhachSan.setText(ghichu.diaChiKSGhiChu);
        _moTaKhachSan.setText(ghichu.moTaGhiChu);
        Bitmap bitmap = BitmapFactory.decodeByteArray(ghichu.anhKhachSan,0 ,ghichu.anhKhachSan.length);
        _anhKhachSan.setImageBitmap(bitmap);

        // Bắut ự kiện cho nút Button  Sữa và Xóa Ghi Chú
        _btnSuaGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent_SuaGhiChu = new Intent(context, SuaGhiChu_Activity.class);
                // Truyền vào ID Để sữa khách sạn
                itent_SuaGhiChu.putExtra("idNote", ID1);
                context.startActivity(itent_SuaGhiChu);
            }
        });
        _btnXoaGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc  muốn xóa Ghi Chú này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(ghichu.idGhiChu);
                        Toast.makeText(context,"ĐÃ XÓA THÀNH CÔNG " +ghichu.tenGhiChu,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Tên Khách Sạn : "+ghichu.tenKSGhiChu,Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }
    private void  delete(int idGhiChu){
        SQLiteDatabase database = Database.initDatabase(context, DATABASE_NAME);
        database.delete("Hotel_Note", "idNote = ?", new String[]{idGhiChu + ""});
        listGhiChu.clear();

        Cursor cursor = database.rawQuery("SELECT * FROM Hotel_Note", null);
        while (cursor.moveToNext()){
            int _id = cursor.getInt(0);
            String _tenGhiChu = cursor.getString(1);
            String _tenKhachSan = cursor.getString(2);
            String _diaChiGhiChu = cursor.getString(5);
               String _date = String.valueOf(cursor.getString(4));
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Date date =null ;
//                try {
//                    date = (Date) formatter.parse(_date);
//                } catch (ParseException e) {
//                e.printStackTrace();
//            }
            String _moTaGhiChu = cursor.getString(3);
            byte[] _anhKhachSan = cursor.getBlob(6);
            listGhiChu.add(new GhiChu(_id,_tenGhiChu,_tenKhachSan,_moTaGhiChu,new Date(),_diaChiGhiChu,_anhKhachSan));
        }
        notifyDataSetChanged();
    }
    }

