package com.example.thuan.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class btnBangGiaVe_Activity extends AppCompatActivity {
//Ánh xạ DB Bang Gia Ve qua Class để hiển thị dữ liệu lên

    //Khai báo hàm sử dụng DATABASE
    //Khai báo dữ liệu Class
    ArrayList<class_BangGiaVeXeMienPhi> listMP;
    final String DATABASE_NAME = "list_bus1.sqlite";
    SQLiteDatabase database_BangGiaXeMienPhi;
     private  ListView listViewMienPhi;
    Adapter_BangGiaVeMP  adapterMP;





    //Tạo dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banggiavexe);

        // Khai đỗ dữ liệu từ Dataabse sang màn hình
        KhoiTao();
       readData();

    }
    //Xây dựng phương thức khởi tạo
    private void KhoiTao(){
        listViewMienPhi = (ListView) findViewById(R.id.listViewMienPhi);
        listMP = new ArrayList<>();
        adapterMP = new Adapter_BangGiaVeMP(this,listMP);
        listViewMienPhi.setAdapter(adapterMP);

    }
    private void readData(){
        database_BangGiaXeMienPhi = DBBangGiaXe.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database_BangGiaXeMienPhi.rawQuery("SELECT * FROM MIENPHI_GIAVE ", null);
        listMP.clear();
        //Chạy vòng for để aadd dữ liệu
        for(int i = 0 ; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id= cursor.getInt(0);
            String ten  = cursor.getString(1);
            String moTa = cursor.getString(2);
            listMP.add(new class_BangGiaVeXeMienPhi(id,ten,moTa));
        }
        adapterMP.notifyDataSetChanged();
        // cursor.moveToFirst();
        //Toast.makeText(this ,cursor.getString(1),Toast.LENGTH_SHORT ).show();

    }
}














