package com.example.thuan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BangGiaVeActivity  extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubanggiave);

      final Button btnXemBangGiaVe = (Button) findViewById(R.id.btnXemGiaVe);
        final Button  btnTraCuuTuyenXe = (Button) findViewById(R.id.btnTraCuuTuyenXe);
         btnXemBangGiaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showToastBangGia();
                Intent intentBangGiaVe = new Intent(BangGiaVeActivity.this, btnBangGiaVe_Activity.class);
                startActivity(intentBangGiaVe);
            }
        });


        btnTraCuuTuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastTraCuuTuyenXe();
                Intent intentTraCuuTuyenXe = new Intent(BangGiaVeActivity.this, btnTraCuuTuyenXe.class);
                startActivity(intentTraCuuTuyenXe);
            }
        });


    }
    public void showToastBangGia(){


        Context context = getApplicationContext();
        CharSequence text = "CHUYỂn ĐẾN ƯU TIÊN - MIỄN PHÍ  !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }

    public void showToastTraCuuTuyenXe(){


        Context context = getApplicationContext();
        CharSequence text = "CHUYỂN ĐẾN BẢNG GIÁ VÉ  !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }


}
