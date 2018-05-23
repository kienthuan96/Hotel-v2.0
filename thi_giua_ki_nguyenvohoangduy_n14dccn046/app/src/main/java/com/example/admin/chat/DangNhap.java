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

public class DangNhap extends AppCompatActivity {

    EditText etus;
    EditText etpass;
    Button btndn;
    Button btndk;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        etpass= (EditText) findViewById(R.id.etPassDN);
        etus= (EditText) findViewById(R.id.etUsernameDN);
        btndk= (Button) findViewById(R.id.btnSignup);
        btndn= (Button) findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhap.this,DangKy.class);
                startActivity(intent);
            }
        });

        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangnhap();
            }
        });
    }

    private void Dangnhap(){
        String email=etus.getText().toString();
        String pass=etpass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangNhap.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DangNhap.this,MainActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("username",etus.getText().toString());
                            intent.putExtra("UsernameDN",bundle);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(DangNhap.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
