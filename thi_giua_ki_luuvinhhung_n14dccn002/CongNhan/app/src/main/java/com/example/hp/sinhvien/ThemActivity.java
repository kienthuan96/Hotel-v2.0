package com.example.hp.sinhvien;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemActivity extends AppCompatActivity {
    EditText edtID,edtTen;
    Button btnLuu;
    SQLiteDatabase sqLiteDatabase;
    final String DATABASE_NAME ="congnhan.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        id();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them(edtID.getText().toString(),edtTen.getText().toString());
            }
        });


    }
    public void id(){
        edtID=findViewById(R.id.edtID);
        edtTen=findViewById(R.id.edtTen);
        btnLuu=findViewById(R.id.btnThem);

    }

    public void them(String id,String ten){
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("HoTen",ten);
        sqLiteDatabase=DataBase.initDatabase(this,DATABASE_NAME);
        sqLiteDatabase.insert("CongNhan",null,contentValues);
    }
}
