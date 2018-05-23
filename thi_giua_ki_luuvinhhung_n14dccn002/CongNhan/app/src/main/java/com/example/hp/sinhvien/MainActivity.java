package com.example.hp.sinhvien;

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

public class MainActivity extends AppCompatActivity {

    Button btnThem,btnLoad;
    SQLiteDatabase sqLiteDatabase;
    final String DATABASE_NAME = "congnhan.sqlite";
    ArrayList<CongNhan> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem=findViewById(R.id.btnThem);
        btnLoad=findViewById(R.id.btnLoad);
        list = new ArrayList<>();
        listView = findViewById(R.id.lv);
        readData();
        final Adapte adapte = new Adapte(MainActivity.this,R.layout.layout_item,list);
        listView.setAdapter(adapte);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
                adapte.notifyDataSetChanged();
            }
        });
    }



    private  void readData() {
        sqLiteDatabase = DataBase.initDatabase(this, DATABASE_NAME);
        list.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SinhVien", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String hoten = cursor.getString(1);
            CongNhan congNhan = new CongNhan(id, hoten);
            list.add(congNhan);
            Toast.makeText(this,cursor.getCount()+"", Toast.LENGTH_LONG).show();


        }
    }
}
