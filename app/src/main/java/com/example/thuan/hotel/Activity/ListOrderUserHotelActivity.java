package com.example.thuan.hotel.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.thuan.hotel.Adapter.Adapter_OderUser_Hotel;
import com.example.thuan.hotel.Adapter.Adapter_Order_Hotel;
import com.example.thuan.hotel.Model.Hotel;
import com.example.thuan.hotel.Model.Oder;
import com.example.thuan.hotel.Model.OrderUser;
import com.example.thuan.hotel.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListOrderUserHotelActivity   extends AppCompatActivity {
    public DatabaseReference def;
    Firebase myFB;
    private List<OrderUser> mOderusList ;
    private Adapter_OderUser_Hotel adapter;
    ListView lvOrderuserHotel;
    private List<Hotel> mKhachSanList ;
    private FirebaseAuth mAuth;
    // TextView txtHoTen,
    private void addlisthotel()
    {
        //     Log.v("YourValue,", "seach is:" + seachstring);
        def = FirebaseDatabase.getInstance().getReference("hotel");
        def.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    HashMap t = (HashMap) childSnapshot.getValue();

                    SetSeach(t.get("name").toString(),t.get("address").toString(),t.get("id").toString());

                }


                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void SetSeach(final String NameHotel,final String DiaChi,final String id)
    {
        def = FirebaseDatabase.getInstance().getReference("order");
        def.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    HashMap t = (HashMap) childSnapshot.getValue();
                   if(id.equals(t.get("hotel_id").toString())) {
                       mAuth = FirebaseAuth.getInstance();
                       FirebaseUser user = mAuth.getCurrentUser();
                       if(user.getUid().equals(t.get("user_order_id").toString())) {
                           mOderusList.add(new OrderUser(NameHotel, DiaChi, t.get("DateStarOrder").toString(), t.get("DateEndOrder").toString(), Integer.parseInt(t.get("RoomOrder").toString()), Float.parseFloat(t.get("TotalMoney").toString())
                           ));
                       }
                   }

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listuserorder);
        mOderusList = new ArrayList<>();
        mKhachSanList = new ArrayList<>();
        adapter  = new Adapter_OderUser_Hotel(getApplicationContext(), mOderusList);
        lvOrderuserHotel = findViewById(R.id.dsOrderUser);
        lvOrderuserHotel.setAdapter(adapter);
      /*  Firebase.setAndroidContext(this);
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        myFB = new Firebase("https://hotel-793b0.firebaseio.com/order");
        def = FirebaseDatabase.getInstance().getReference();*/

        addlisthotel();
        //  SetSeach();
    }

}
