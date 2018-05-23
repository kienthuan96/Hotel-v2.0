package com.example.admin.chat;

import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etchat;
    Button btnsend;
    ListView lsvchat;
    ArrayList<String> listchat;
    ArrayAdapter arrayAdapter;
    public static String user;

    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etchat= (EditText) findViewById(R.id.etChat);
        btnsend= (Button) findViewById(R.id.btnSend);
        lsvchat= (ListView) findViewById(R.id.lsvChat);
        listchat=new ArrayList<String>();
        arrayAdapter =new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listchat);
        lsvchat.setAdapter(arrayAdapter);

        Intent callerIntent = this.getIntent();
        Bundle packageFromCaller = new Bundle();
        packageFromCaller = callerIntent.getBundleExtra("UsernameDN");
        user = packageFromCaller.getString("username");

        mData= FirebaseDatabase.getInstance().getReference();

        mData.child("NOIDUNGCHAT").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messenger ms=dataSnapshot.getValue(Messenger.class);
                listchat.add(ms.getId()+":"+ms.getMessage());
                arrayAdapter.notifyDataSetChanged();
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

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messenger ms=new Messenger();
                ms.setId(user);
                ms.setMessage(etchat.getText().toString());
                mData.child("NOIDUNGCHAT").push().setValue(ms);
            }
        });

    }
}
