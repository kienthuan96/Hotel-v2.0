package com.example.thuan.vdt2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChinhActivity extends AppCompatActivity {
    EditText edttenDT,edtgiaDT;
    Button btnSave;
    private String DATABASE_NAME="sqlite.sqlite";
    SQLiteDatabase sqLiteDatabase;
    String tenDT,giaDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh);
        edttenDT=findViewById(R.id.edtTenDT);
        edtgiaDT=findViewById(R.id.edtGiaDt);
        btnSave=findViewById(R.id.btnSave);

        tenDT=new String(edttenDT.getText().toString());
        giaDT=new String (edtgiaDT.getText().toString());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(edttenDT.getText().toString(),edtgiaDT.getText().toString());
            }
        });

    }

    private void saveData(String ten, String gia){
        ContentValues contentValues= new ContentValues();
        contentValues.put("ten",ten);
        contentValues.put("gia",gia);
        sqLiteDatabase=DB.initDatabase(this,DATABASE_NAME);
        sqLiteDatabase.insert("DienThoai",null,contentValues);
        Toast.makeText(ChinhActivity.this,"Thanh cong",Toast.LENGTH_LONG).show();
    }

}
