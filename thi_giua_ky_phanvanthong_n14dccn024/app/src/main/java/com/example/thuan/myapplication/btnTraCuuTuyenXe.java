package com.example.thuan.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class btnTraCuuTuyenXe extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    final String DATABASE_NAME = "list_bus1.sqlite";
    ArrayList<class_BangGiaVeXe> list;
    ListView listView;
    Adapter_BangGiaVe adapter;
    SQLiteDatabase database_BangGiaXe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracuutuyenxe);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewAnh1);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);





        KhoiTao();
        readData();

        // Boolean isAutoStart = viewFlipper.isAutoStart();
    }

    private void KhoiTao() {

        listView = (ListView) findViewById(R.id.listViewTuyenXe);
        list = new ArrayList<>();
        adapter = new Adapter_BangGiaVe(this, list);
        listView.setAdapter(adapter);
    }
    private void readData(){
        try {
            database_BangGiaXe = DBBangGiaXe.initDatabase(this, DATABASE_NAME);
            Cursor cursor = database_BangGiaXe.rawQuery("SELECT * FROM BANG_GIAVE", null);
            list.clear(); // để tránh đọc 2 lần 1 row
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                String hinhThuc = cursor.getString(1);
                String doiTuong = cursor.getString(2);
                int giaVe = cursor.getInt(3);
                int chieuDai = cursor.getInt(4);
                list.add(new class_BangGiaVeXe(id, hinhThuc, doiTuong, giaVe, chieuDai));


            }
            adapter.notifyDataSetChanged(); // Adapter vẽ lại giao diện
        }catch (Exception e){
            e.getMessage();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý thanh công cụ mục nhấp vào đây. Thanh tác vụ sẽ
        // tự động xử lý các nhấp chuột vào nút Home / Up, do đó, dài
        // khi bạn chỉ định hoạt động của cha mẹ trong AndroidManifest.xml.
        int id = item.getItemId();

        //không kiểm tra
        if (id == R.id.always) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
