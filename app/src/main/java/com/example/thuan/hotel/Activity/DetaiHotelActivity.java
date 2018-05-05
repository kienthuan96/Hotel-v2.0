package com.example.thuan.hotel.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuan.hotel.Adapter.Adapter_BinhLuan;
import com.example.thuan.hotel.Adapter.Adapter_Image_Hotel;
import com.example.thuan.hotel.Helper.Database;
import com.example.thuan.hotel.Model.BinhLuan;
import com.example.thuan.hotel.Model.Hotel;
import com.example.thuan.hotel.Model.Service;
import com.example.thuan.hotel.Model.Program;
import com.example.thuan.hotel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.bloder.magic.view.MagicButton;

public class DetaiHotelActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    final String DATABASE_NAME = "database.sqlite";
    SQLiteDatabase databaseSQL;
    TabHost tabHost;
        Program pr;

    String id_hotel="";
    //Khai bao 2 Button Them va Thoat Comment
    private Button ThemBL;
    private Button ThoatBL;
    private ListView listView_Comment ;
    private Adapter_BinhLuan adapter_binhLuan;
    private ArrayList<BinhLuan> data;
    DatabaseReference mDatabase;
    DatabaseReference myRef;
  private   float tongDiem  =0 ;
    private int countBL= 0 ;
    public  float diemTBkhachSan ;


    ImageView imgWifi,imgBar,imgRestaurant,imgSwimmingPool,imgPet;
    TextView txtTenKS,txtDiaChiKS,txtGiaKS,txtSDTKS;
    ImageView img;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Hotel hotel;
    RatingBar ratingBar;
    MagicButton clickFavorite;
    ArrayList<String> arrayListHinhAnh;
    ListView lstHinhAnh;
    Animation aniName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_hotel);
        id();
        // INIT
        Intent intent = this.getIntent();
//        if(intent !=null) {
//
            Bundle bundle = intent.getBundleExtra("goi");
            id_hotel = bundle.getString("id");
//        }
//        else {
//            id_hotel = "-LAqUFdTyh6UXVtnASG9";
//        }

        hotel=new Hotel();



        KhoiTao_BinhLuan();



        tabHost.setup();

        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setIndicator("Thông tin");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setIndicator("Nhận xét");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3=tabHost.newTabSpec("t3");
        tab3.setIndicator("Hình ảnh");
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab3);



        load();
        readData_BinhLuan();
        //Toast.makeText(DetaiHotelActivity.this,"Load",Toast.LENGTH_SHORT).show();
        clickFavorite.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEventFavorite();
            }
        });


        // Bat su kien cho nut Button Them va Thoat

        ThoatBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Thoat = new Intent(DetaiHotelActivity.this, MainActivity.class);
                startActivity(intent_Thoat);
                Toast.makeText(DetaiHotelActivity.this, "Chuyển Đến Menu", Toast.LENGTH_SHORT).show();
            }
        });
        ThemBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ThemBL = new Intent(DetaiHotelActivity.this, ThemBinhLuanActivity.class);
                startActivity(intent_ThemBL);
                Toast.makeText(DetaiHotelActivity.this, "Chuyển đến Thêm Bình Luận", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void load(){
        myRef = database.getReference("hotel").child(id_hotel);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hotel= dataSnapshot.getValue(Hotel.class);
                txtTenKS.setText(hotel.getName());
                txtSDTKS.setText(hotel.getNumberPhone()+"");
                txtGiaKS.setText(hotel.getPrice()+"");
                txtDiaChiKS.setText(hotel.getAddress());
                ratingBar.setRating(Float.parseFloat(hotel.getStars().toString()));

                    //Nhận dữ liệu truyền qua Comment
                  Program.tenKhachSan   = hotel.getName();
                  Program.IDKhachSan= hotel.getId();
//                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/hotel-793b0.appspot.com/o/IMG_CONTACT%2F"
//                        +hotel.getImg1()+"?alt=media&token=d5f61a15-07d0-4f70-8ed8-0fa389da9e52").into(img);

//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReference();
//                StorageReference pathReference = storageRef.child("IMG_CONTACT/"+hotel.getImg1());
//                Glide.with(DetaiHotelActivity.this).using(new FirebaseImageLoader()).load(pathReference).into(img);

                Service service=hotel.getService();
                if(service.getWifi())   imgWifi.setVisibility(View.VISIBLE); else imgWifi.setVisibility(View.INVISIBLE);
                if(service.getPet())   imgPet.setVisibility(View.VISIBLE); else imgPet.setVisibility(View.INVISIBLE);
                if(service.getRestaurant())   imgRestaurant.setVisibility(View.VISIBLE); else imgRestaurant.setVisibility(View.INVISIBLE);
                if(service.getSwimmingPool())   imgSwimmingPool.setVisibility(View.VISIBLE); else imgSwimmingPool.setVisibility(View.INVISIBLE);
                if(service.getWifi())   imgWifi.setVisibility(View.VISIBLE); else imgWifi.setVisibility(View.INVISIBLE);

                arrayListHinhAnh.clear();
                arrayListHinhAnh.add(hotel.getImg1());
                arrayListHinhAnh.add(hotel.getImg2());
                arrayListHinhAnh.add(hotel.getImg3());
                //Toast.makeText(DetaiHotelActivity.this, "So luong hinh: "+arrayListHinhAnh.get(2),Toast.LENGTH_SHORT).show();
                Adapter_Image_Hotel adapter_image_hotel=new Adapter_Image_Hotel(DetaiHotelActivity.this,R.layout.layout_item_image,arrayListHinhAnh);
                lstHinhAnh.setAdapter(adapter_image_hotel);
                adapter_image_hotel.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DetaiHotelActivity.this,"That bai",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void id(){
        tabHost=findViewById(R.id.tabhost);
        txtTenKS=findViewById(R.id.txtTenKS);
        txtSDTKS=findViewById(R.id.txtSDTKS);
        txtDiaChiKS=findViewById(R.id.txtDiaChiKS);
        txtGiaKS=findViewById(R.id.txtGiaKS);
        img=findViewById(R.id.imgHotel);
        ratingBar=findViewById(R.id.rbKS);
        ratingBar.setEnabled(false);
        clickFavorite=findViewById(R.id.clickFavorite);
        imgWifi=findViewById(R.id.imgWifi);
        imgPet=findViewById(R.id.imgPet);
        imgBar=findViewById(R.id.imgBar);
        imgRestaurant=findViewById(R.id.imgRestaurant);
        imgSwimmingPool=findViewById(R.id.imgSwimmingPool);
        arrayListHinhAnh=new ArrayList<>();
        lstHinhAnh=findViewById(R.id.lstImage);
        aniName= AnimationUtils.loadAnimation(this,R.anim.name_hotel);
    }

    private void addEventFavorite() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null) {
            Intent intent = new Intent(DetaiHotelActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user.getUid());
        contentValues.put("hotel_id", id_hotel);

        databaseSQL = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = databaseSQL.rawQuery("SELECT * FROM favorite where user_id = '" + user.getUid()  +"' AND hotel_id = '"+ id_hotel +"'",null);
        if(cursor.getCount() != 0) {
            Toast.makeText(DetaiHotelActivity.this, "Khách sạn này đã thêm vào danh sách yêu thích của bạn", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseSQL.insert("favorite",null, contentValues);
            Toast.makeText(DetaiHotelActivity.this, "Thêm vào danh sách yêu thích thành công", Toast.LENGTH_SHORT).show();
        }

        lstHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void KhoiTao_BinhLuan(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
// Anh xa 2 Button Comment

        ThemBL = (Button) findViewById(R.id.btnThemBL);
        ThoatBL =(Button) findViewById(R.id.btnThoat);
        // ÁNh xạ listView
        listView_Comment = (ListView) findViewById(R.id.listComment);
        //Khởi tạo mảng và ánh xạ Adapter vào listView
        data = new ArrayList<>();
        adapter_binhLuan = new Adapter_BinhLuan(this,data);
        listView_Comment.setAdapter(adapter_binhLuan);

    }
    private void readData_BinhLuan(){
        // ĐƯa id của hotel
        mDatabase.child("hotel").child(id_hotel).child("BinhLuan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BinhLuan bl = dataSnapshot.getValue(BinhLuan.class);
                tongDiem += bl.DiemBL;

                data.add(bl);
                countBL = data.size();
                diemTBkhachSan = (float)tongDiem / countBL; // tính trung binhf cho nay
                Toast.makeText(DetaiHotelActivity.this, "ĐIểm trung bình " + diemTBkhachSan, Toast.LENGTH_SHORT).show();
             //   Toast.makeText(DetaiHotelActivity.this, "Đã load dữ liệu thành công! ", Toast.LENGTH_SHORT).show();
                adapter_binhLuan.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }
    public static DatabaseReference def;
    Button btnDat;

    private void setOder(final  String manguoidung,final  String makhachsan)
    {
        def = FirebaseDatabase.getInstance().getReference("hotel");
        def.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int flag =0;
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    HashMap t = (HashMap) childSnapshot.getValue();


                    if(manguoidung.equals(t.get("id_user").toString()))
                    {
                     String key = t.get("id").toString();
                       // Toast.makeText(DetaiHotelActivity.this, makhachsan+"mks", Toast.LENGTH_LONG).show();
                        if(makhachsan.equals(key))
                        {
                            Toast.makeText(DetaiHotelActivity.this, "Bạn không thể đặt khách sạn của mình", Toast.LENGTH_LONG).show();
                       //     Intent intent=new Intent(DetaiHotelActivity.this,DetaiHotelActivity.class);
                          flag=1;  //Intent intent=new Intent(DetaiHotelActivity.this,OrderActivity.class);
                            //Bundle bundle5=new Bundle();
                            //bundle5.putString("id45","1");
                            //intent.putExtra("goi45",bundle5);
                            //      Toast.makeText(DetaiHotelActivity.this,user.getUid(),Toast.LENGTH_LONG).show();
                      //      startActivity(intent);

                                return;
                            //Toast.makeText(OrderActivity.this, id_user + id_hotel, Toast.LENGTH_LONG).show();

                        }


                    }




                }
                Toast.makeText(DetaiHotelActivity.this, flag+"", Toast.LENGTH_LONG).show();
                if(flag!=1)
                {
                    Bundle bundle1=new Bundle();
                    Bundle bundle3=new Bundle();
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent=new Intent(DetaiHotelActivity.this,OrderActivity.class);
                    bundle1.putString("id1",user.getUid());
                    intent.putExtra("goi1",bundle1);
                    bundle3.putString("id",hotel.getId());
                    intent.putExtra("goi",bundle3);
                    //      Toast.makeText(DetaiHotelActivity.this,user.getUid(),Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuBook:
                String mauser="4FyLeXHjvHUVm7VDuzXPR1ZRLki2",maks ="-LAqUFdTyh6UXVtnASG9";
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
               /* if(user == null) {
                    Intent intent = new Intent(DetaiHotelActivity.this, LoginActivity.class);
                    startActivity(intent);
                }*/
                setOder(user.getUid(),hotel.getId());

             //   Intent intent=new Intent(DetaiHotelActivity.this,OrderActivity.class);


                return true;
            case R.id.menuMap:
                Intent intent2=new Intent(DetaiHotelActivity.this,GoogleMapActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("address",hotel.getAddress());
                intent2.putExtra("goi",bundle2);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
