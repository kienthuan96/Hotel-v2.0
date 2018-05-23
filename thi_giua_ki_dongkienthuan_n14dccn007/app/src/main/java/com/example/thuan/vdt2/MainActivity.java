package com.example.thuan.vdt2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Phone> arrayList;
    Adapter_Phone adapter_phone;
    private String DATABASE_NAME="sqlite.sqlite";
    Button btnChinh,btnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChinh=findViewById(R.id.btnChinh);
        btnLoad=findViewById(R.id.btnLoad);
        listView=findViewById(R.id.listView);
        arrayList=new ArrayList<>();
        readData();
        adapter_phone=new Adapter_Phone(MainActivity.this, R.layout.layout_item_phone,arrayList);
        listView.setAdapter(adapter_phone);


        btnChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ChinhActivity.class);
                startActivity(intent);
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
                adapter_phone.notifyDataSetChanged();
            }
        });
    }

    private void readData() {
        sqLiteDatabase = DB.initDatabase(this, DATABASE_NAME);
        arrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DienThoai", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String tenPhone = cursor.getString(1);
            String giaPhone = cursor.getString(2);
            arrayList.add(new Phone(tenPhone,giaPhone));
        }
    }
}
