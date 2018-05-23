package com.example.admin.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKy extends AppCompatActivity {

    EditText etemail;
    EditText etpass;
    Button btndk;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        mAuth = FirebaseAuth.getInstance();

        etemail= (EditText) findViewById(R.id.etEmail);
        etpass= (EditText) findViewById(R.id.etPass);
        btndk= (Button) findViewById(R.id.btnDK);

        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangky();
            }
        });
    }

    private void Dangky(){
        String email=etemail.getText().toString();
        String pass=etpass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DangKy.this,DangNhap.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(DangKy.this,"Đăng ký thất bại",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
