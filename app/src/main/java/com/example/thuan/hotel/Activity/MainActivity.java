package com.example.thuan.hotel.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thuan.hotel.DeleteActivity;
import com.example.thuan.hotel.R;
import com.example.thuan.hotel.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yasic.library.particletextview.MovingStrategy.RandomMovingStrategy;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextView;

import br.com.bloder.magic.view.MagicButton;


public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister, btnPost, btnList, btnSearch, btnFavorite,btnDetail,btnDatPhong, btnDelete,btnDanhsachdat, btnGoogleMap,btnDanhsachdatuser;
    FirebaseUser user;
    Context context;
    MagicButton magicButton;
    ParticleTextView particleTextView;
    public static final int REQUEST_CODE_REGISTER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        anhXa();

    }
    private void danhsachdath() {
        Intent intent = new Intent(MainActivity.this, ListOderActivity.class);
        startActivity(intent);
    }
    private void danhsachdatuser() {
        Intent intent = new Intent(MainActivity.this, ListOrderUserHotelActivity.class);
        startActivity(intent);
    }
    private void search() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    private void Datphong() {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    private void register() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void post(){
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        startActivity(intent);
    }

    private void list(){
        Intent intent = new Intent(MainActivity.this, ListHotelActivity.class);
        startActivity(intent);
    }

    private void favorite(){
        Intent intent = new Intent(MainActivity.this, FavoriteHotelActivity.class);
        startActivity(intent);
    }
    private void detail(){
        Intent intent = new Intent(MainActivity.this, DetaiHotelActivity.class);
        startActivity(intent);
    }
    private void delete(){
        Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(intent);
    }

    private void googleMap(){
        Intent intent = new Intent(MainActivity.this, GoogleMapActivity.class);
        startActivity(intent);
    }
    private  void particleTextView(){
        ParticleTextViewConfig config=new ParticleTextViewConfig.Builder()
                .setRowStep(7)
                .setColumnStep(7)
                .setTargetText("Kien Thuan")
                .setReleasing(0.2)
                .setParticleRadius(4)
                .setMiniDistance(0.1)
                .setTextSize(120)
                .setMovingStrategy(new RandomMovingStrategy())
                .instance();
        particleTextView.setConfig(config);
        particleTextView.startAnimation();
    }

    private void anhXa() {
        btnLogin =  findViewById(R.id.btnLogin);
//        btnRegister =  findViewById(R.id.btnRegister);
        btnPost=findViewById(R.id.btnPost);
        btnList=findViewById(R.id.btnList);
        btnDetail=findViewById(R.id.btnSplash);
//        btnFavorite = findViewById(R.id.btnFavorite);
        btnSearch = findViewById(R.id.btnSearch);
        btnGoogleMap = findViewById(R.id.btnGoogleMap);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        btnDanhsachdat = findViewById(R.id.btnDanhsachdat);
        magicButton=findViewById(R.id.mbtnMagic);
        particleTextView=findViewById(R.id.particleTextView);

        btnDanhsachdatuser = findViewById(R.id.btnDanhsachdatuser);
        btnDanhsachdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhsachdath();
            }
        });
        btnDanhsachdatuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhsachdatuser();
            }
        });
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datphong();
            }
        });

        btnDelete=findViewById(R.id.btnDelete);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                register();
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

//        btnList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                list();
//            }
//        });

//        btnFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                favorite();
//            }
//        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                detail();
                Intent intent=new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap();
            }
        });
        magicButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Magic", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
