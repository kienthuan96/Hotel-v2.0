package com.example.thuan.hotel.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.thuan.hotel.Helper.Database;
import com.example.thuan.hotel.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThemGhiChu_Activity extends AppCompatActivity {
    // Khai báo các class và các Button
    private Button  btnThemGhiChu , btnThoatThemGhiChu , btnThemChonHinh ,  btnThemChupHinh ;
    private EditText themTenGhiChu , themTenKhachSan , themDiaChiKhachSan ,themNhanXetKhachSan ;
    private ImageView themAnhKhachSan ;
    private Date date ;

    final int REQUEST_TAKE_PHOTO =123;
    final int REQUEST_CHOOSE_PHOTO = 321;


    // Khai báo cơ sở dữ liệu
    private   String DATABASE_NAME = "Hotel_NoteOffline.sqlite";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themghichu);
        loadData();
        addEventChonvaChupHinh();
    }
    private void loadData(){
        //Button
        btnThemGhiChu = (Button) findViewById(R.id.btnThemGhiChu);
        btnThoatThemGhiChu = (Button) findViewById(R.id.btnThemThoatThemGhiChu);
        btnThemChonHinh = (Button) findViewById(R.id.btnThemChonAnh);
        btnThemChupHinh = (Button) findViewById(R.id.btnThemChupAnh);
        // id layout
        themTenGhiChu = (EditText)findViewById(R.id.itemThemTenGhiChu);
        themTenKhachSan = (EditText)findViewById(R.id.itemThemTenKhachSan);
        themDiaChiKhachSan = (EditText)findViewById(R.id.itemThemDiaChiKhachSan);
        themNhanXetKhachSan = (EditText)findViewById(R.id.itemThemNhanXetKhachSan);
        themAnhKhachSan = (ImageView ) findViewById(R.id.itemAnhKhachSan);
        date = new Date();

    }
    private void chupHinh(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent , REQUEST_TAKE_PHOTO);
    }
    private void chonHinh(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent , REQUEST_CHOOSE_PHOTO);
    }
    private void addEventChonvaChupHinh(){
        btnThemChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonHinh();
            }
        });
        btnThemChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chupHinh();
            }
        });
        btnThemGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGhiChu();
            }
        });
        btnThoatThemGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Thoat = new Intent(ThemGhiChu_Activity.this, MenuGhiChuActivity.class);
                startActivity(intent_Thoat);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // xử lý các sự kiện chụp và chọn hình
        if(resultCode == RESULT_OK){
            // xự kiện chọn hình
            if(resultCode == REQUEST_CHOOSE_PHOTO){

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    //SỬ dụng hàm Decode để Bitmap
                    Bitmap bitmapchooseImage =  BitmapFactory.decodeStream(is);
                    themAnhKhachSan.setImageBitmap(bitmapchooseImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmapTakePhoto =  (Bitmap) data.getExtras().get("data");
                themAnhKhachSan.setImageBitmap(bitmapTakePhoto);
            }
        }
    }
    private void themGhiChu(){
        // Lấy data từ cái Editext
        String _itemSuaTenGhiChu = themTenGhiChu.getText().toString();
        String _itemSuaTenKhachSan = themTenKhachSan.getText().toString();
        String _itemSuaDiaChiKhachSan = themDiaChiKhachSan.getText().toString();
        String _itemSuaNhanXet =  themNhanXetKhachSan.getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String _date  = String.valueOf(new Date());
      //  Date date = null;
//        try {
//            date = formatter.parse(_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        byte[] _itemAnhDaiDien = getByteArrayFromImageView(themAnhKhachSan);

        // Sử dụng content values để add data vào CSDL
        ContentValues contentData = new ContentValues();
        contentData.put("tenNote", _itemSuaTenGhiChu);
        contentData.put("tenKhachSan",_itemSuaTenKhachSan );
        contentData.put("moTaNote",_itemSuaNhanXet);
        //   contentData.put("ngayTaoNote",dateFormat.format(date) );
        date =new Date();
        contentData.put("ngayTaoNote",_date);
        contentData.put("diaChiKhachSan" ,_itemSuaDiaChiKhachSan);
        contentData.put("hinhAnhKhachSan" ,_itemAnhDaiDien);

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.insert("Hotel_Note",null , contentData);
        Intent intent = new Intent(this, DanhSachGhiChu_Activity.class);
        startActivity(intent);


    }
    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
