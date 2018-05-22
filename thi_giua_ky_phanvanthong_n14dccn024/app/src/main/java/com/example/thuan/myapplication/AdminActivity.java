package com.example.thuan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    private EditText etHoTen;
    private EditText etDiaChi;
    private ListView lvDanhSach;
    private RadioButton rdBinhThuong;
    private RadioButton rdKhuyetTat;
    private Button btnThem;
    private Button btnThoat;

    ArrayList<Bus> arrBus;
    SqliteAdapter sqliteAdapter;
    DBManager dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        id();

        dbHelper = new DBManager(AdminActivity.this);
        arrBus= new ArrayList<Bus>();
        //dbHelper.addBus(new Bus("02","Mien Tay - Bến thành"));
        arrBus=dbHelper.getAllBus();
        //Toast.makeText(AdminActivity.this,"Thanh cong",Toast.LENGTH_LONG).show();


        Toast.makeText(AdminActivity.this,"Du lieu: "+arrBus.get(1).getTenXe(),Toast.LENGTH_LONG).show();
    }

    private void id() {
        lvDanhSach=findViewById(R.id.lvDanhSach);

    }
}
