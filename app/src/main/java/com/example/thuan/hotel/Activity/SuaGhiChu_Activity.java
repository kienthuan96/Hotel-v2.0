package com.example.thuan.hotel.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SuaGhiChu_Activity extends AppCompatActivity {
    private   String DATABASE_NAME = "Hotel_NoteOffline.sqlite";

    private Button  btnSuaChonAnh ,btnSuaChupAnh , btnSua,btnThoat ;
    private ImageView  imgitemAnh ;
    private EditText itemTenGhiChu,itemTenKhachSan , itemMotaGhiChu ,itemDiaChiKhachSan ;

    final int REQUEST_TAKE_PHOTO =123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    // Khai báo biến id là toàn cục
    int id=-1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suaghichu);
        loadData();
        getData();
        addEventChonvaChupHinh();



    }
    private void loadData(){
        btnSuaChonAnh = (Button) findViewById(R.id.btnSuaChonAnh);
        btnSuaChupAnh = (Button) findViewById(R.id.btnSuaChupAnh);
        btnSua = (Button) findViewById(R.id.btnSuaUpdateGhiChu);
        btnThoat = (Button) findViewById(R.id.btnSuaThoatGhiChu);

        imgitemAnh =(ImageView) findViewById(R.id.itemSuaAnh);
        itemTenGhiChu = (EditText) findViewById(R.id.itemSuaTenGhiChu);
        itemTenKhachSan = (EditText) findViewById(R.id.itemSuaTenKhachSan);
        itemMotaGhiChu = (EditText) findViewById(R.id.itemSuaNhanXetKhachSan);
        itemDiaChiKhachSan = (EditText) findViewById(R.id.itemSuaDiaChiKhachSan);


    }
    private void getData(){
        Intent intent  = getIntent();
        id = intent.getIntExtra("idNote" , -1);
        SQLiteDatabase database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor =database.rawQuery("SELECT * FROM Hotel_Note where idNote = ?",new String[]{id+""});
        cursor.moveToFirst();
        String _tenGhiChu = cursor.getString(1);
        String _tenKhachSan = cursor.getString(2);
        String _diaChiGhiChu = cursor.getString(5);
        String _moTaGhiChu = cursor.getString(3);
        byte[] _anhKhachSan = cursor.getBlob(6);
        Bitmap bitmap = BitmapFactory.decodeByteArray(_anhKhachSan,0 ,_anhKhachSan.length);
        imgitemAnh.setImageBitmap(bitmap);
        //Ánh xạ lên giao diện
        itemTenGhiChu.setText(_tenGhiChu);
        itemTenKhachSan.setText(_tenKhachSan);
        itemMotaGhiChu.setText(_moTaGhiChu);
        itemDiaChiKhachSan.setText(_diaChiGhiChu);

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
        btnSuaChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonHinh();
            }
        });
        btnSuaChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chupHinh();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Thoat = new Intent(SuaGhiChu_Activity.this, DanhSachGhiChu_Activity.class);
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
                    imgitemAnh.setImageBitmap(bitmapchooseImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO){
                        Bitmap bitmapTakePhoto =  (Bitmap) data.getExtras().get("data");
                      imgitemAnh.setImageBitmap(bitmapTakePhoto);
            }
        }
    }
    private void update(){
        // Lấy data từ cái Editext
        String _itemSuaTenGhiChu = itemTenGhiChu.getText().toString();
        String _itemSuaTenKhachSan = itemTenKhachSan.getText().toString();
        String _itemSuaDiaChiKhachSan = itemDiaChiKhachSan.getText().toString();
        String _itemSuaNhanXet =  itemMotaGhiChu.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        byte[] _itemAnhDaiDien = getByteArrayFromImageView(imgitemAnh);

        // Sử dụng content values để add data vào CSDL
        ContentValues contentData = new ContentValues();
        contentData.put("tenNote", _itemSuaTenGhiChu);
        contentData.put("tenKhachSan",_itemSuaTenKhachSan );
        contentData.put("moTaNote",_itemSuaNhanXet);
     //   contentData.put("ngayTaoNote",dateFormat.format(date) );
        contentData.put("diaChiKhachSan" ,_itemSuaDiaChiKhachSan);
        contentData.put("hinhAnhKhachSan" ,_itemAnhDaiDien);

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.update("Hotel_Note", contentData, "idNote= ?", new String[] {id + ""});
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
