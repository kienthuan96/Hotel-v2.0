package com.example.thuan.hotel.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thuan.hotel.Adapter.Adapter_Favorite_Hotel;
import com.example.thuan.hotel.Helper.Database;
import com.example.thuan.hotel.Model.Hotel;
import com.example.thuan.hotel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteHotelActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ArrayList<String> arrHotelID;
    final String DATABASE_NAME = "database.sqlite";
    SQLiteDatabase databaseSQL;
    ListView lvFavorite;
    ArrayList<Hotel> arrayList;
    Adapter_Favorite_Hotel adapter_hotel;
    DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String hotelID;
    Context context;
    Hotel hotel;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mAuth = FirebaseAuth.getInstance();

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("goi");
        user=bundle.getString("id");

        myRef = database.getReference("hotel");
        init();

        readData(user);
        showData();
        addEvents();
    }

    private void init(){
        hotel           = new Hotel();
        lvFavorite      = findViewById(R.id.lvFavorite);
        arrayList       =  new ArrayList<>();
        arrHotelID      = new ArrayList<>();
        adapter_hotel   = new Adapter_Favorite_Hotel(FavoriteHotelActivity.this, arrayList);
        lvFavorite.setAdapter(adapter_hotel);
    }

    private void checkLogin(FirebaseUser user) {
        if(user == null) {
            Intent intent = new Intent(FavoriteHotelActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void showData() {
        arrayList.clear();
        Log.d("Size", arrHotelID.size() + "");
        if(arrHotelID.size() > 0) {
            for(int i = 0; i < arrHotelID.size(); i++) {
                myRef.child(arrHotelID.get(i)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        hotel = dataSnapshot.getValue(Hotel.class);
                        if(hotel != null) {
                            arrayList.add(hotel);
                            adapter_hotel.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private void readData(String user) {
        arrHotelID.clear();
        databaseSQL = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = databaseSQL.rawQuery("SELECT * FROM favorite where user_id = '" + user  +"'",null);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Hiện vẫn chưa có khách sạn nào trong danh sách yêu thích", Toast.LENGTH_LONG).show();
        }
        else {
            for(int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                String hotel_id = cursor.getString(0);
                arrHotelID.add(hotel_id);
            }
        }

    }


    private void addEvents() {
        lvFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String hotel_id =arrayList.get(position).getId();
                Intent intent = new Intent(FavoriteHotelActivity.this, DetaiHotelActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("id",hotel_id);
                intent.putExtra("goi",bundle1);
                startActivity(intent);
            }
        });

        lvFavorite.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String hotel_id =arrayList.get(position).getId();
                return delete(hotel_id);

            }
        });
    }

    public boolean delete(final String hotel_id){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo")
                .setMessage("Bạn có muốn xóa không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(hotel_id, user);
                        showData();
                        Toast.makeText(FavoriteHotelActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
        return true;
    }

    private void deleteItem(String hotel_ID, String user) {
        databaseSQL.delete("favorite", "user_id = ? and hotel_id = ?", new String[]{user, hotel_ID});
        readData(user);
    }
}