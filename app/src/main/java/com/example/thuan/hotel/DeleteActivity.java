package com.example.thuan.hotel;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.thuan.hotel.Activity.ListHotelActivity;
import com.example.thuan.hotel.Adapter.Adapter_Hotel;
import com.example.thuan.hotel.Model.Hotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
//    ListView lstDelete;
//    String id_user;
//    ArrayList<Hotel> arrayList;
//    Adapter_Hotel adapter_hotel;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef;
    RelativeLayout relativeLayout;
    ImageView img;
    Button btnChuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

//        id();
//        id_user="4FyLeXHjvHUVm7VDuzXPR1ZRLki2";
//
//        myRef = database.getReference("hotel");
//
//        readData();
//        event();
        relativeLayout=findViewById(R.id.relative);
        img=findViewById(R.id.imgVD);
        btnChuyen=findViewById(R.id.btnChuyen);
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeleteActivity.this,Delete_1Activity.class);
                Pair[] pairs=new Pair[1];
                pairs[0]=new Pair<View,String>(img,"hinhanh");
                ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(DeleteActivity.this,pairs);
                startActivity(intent,activityOptions.toBundle());
            }
        });

    }
//    private void readData(){
//        arrayList.clear();
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Hotel hotel=dataSnapshot.getValue(Hotel.class);
//                if(id_user.equals(hotel.getId_user()))  arrayList.add(hotel);
//                adapter_hotel.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void id(){
//        lstDelete=findViewById(R.id.lstDelete);
//        arrayList=new ArrayList<>();
//        adapter_hotel=new Adapter_Hotel(DeleteActivity.this,R.layout.layout_item_hotel,arrayList);
//        lstDelete.setAdapter(adapter_hotel);
//    }
//
//    public void event(){
//        lstDelete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                delete(i);
//                return false;
//            }
//        });
//    }
//
//    public void delete(final int stt){
//        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
//        alertDialog.setTitle("Thông báo")
//                .setMessage("Bạn có muốn xóa không?")
//                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        myRef.child(arrayList.get(stt).getId()).removeValue();
//                        readData();
//                    }
//                })
//                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .show();
//    }
}
