package com.example.thuan.hotel.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thuan.hotel.Adapter.Adapter_Order_Hotel;
import com.example.thuan.hotel.Model.Hotel;
import com.example.thuan.hotel.Model.Oder;
import com.example.thuan.hotel.R;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
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

public class ListOderActivity   extends AppCompatActivity {
    public DatabaseReference def;
    Firebase myFB;
    private List<Oder> mOderList ;
    private Adapter_Order_Hotel adapter;
    ListView lvOrderHotel;
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
               mAuth = FirebaseAuth.getInstance();
               FirebaseUser user = mAuth.getCurrentUser();
               for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                   HashMap t = (HashMap) childSnapshot.getValue();
                   if(user.getUid().equals(t.get("id_user"))) {
                       SetSeach(t.get("id").toString());
                   }

               }


               adapter.notifyDataSetChanged();
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
   }
   private void SetSeach(final String nana)
   {
       def = FirebaseDatabase.getInstance().getReference("order");
       def.addValueEventListener(new ValueEventListener() {

           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                   HashMap t = (HashMap) childSnapshot.getValue();
                    if(nana.equals(t.get("hotel_id").toString())){

                   mOderList.add(new Oder(t.get("name").toString(),t.get("Email").toString(),t.get("Phone").toString(),t.get("DateStarOrder").toString(),t.get("DateEndOrder").toString(),Integer.parseInt(t.get("RoomOrder").toString()),Float.parseFloat(t.get("TotalMoney").toString())
                   ));
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
        setContentView(R.layout.activity_listorder);
        mOderList = new ArrayList<>();
        mKhachSanList = new ArrayList<>();
        adapter  = new Adapter_Order_Hotel(getApplicationContext(), mOderList);
        lvOrderHotel = findViewById(R.id.dsOrder);
        lvOrderHotel.setAdapter(adapter);
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
