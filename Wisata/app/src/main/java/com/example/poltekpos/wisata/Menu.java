package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/5/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class Menu extends Activity implements View.OnClickListener {

    private Button bKendaraan;
    private Button bPengunjung;
    private Button bWisata;
    private Button bUser;
    private Button bNavi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bKendaraan = (Button) findViewById(R.id.button_masuk_kendaraan);
        bKendaraan.setOnClickListener(this);
        bPengunjung = (Button) findViewById(R.id.button_masuk_pengunjung);
        bPengunjung.setOnClickListener(this);
        bWisata = (Button) findViewById(R.id.button_masuk_wisata);
        bWisata.setOnClickListener(this);
        bUser = (Button) findViewById(R.id.button_masuk_user);
        bUser.setOnClickListener(this);
        bNavi = (Button) findViewById(R.id.navi);
        bNavi.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_masuk_kendaraan :
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Data Kendaraan Masuk", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_masuk_pengunjung :
                Intent i2 = new Intent(this, MainActivity2.class);
                startActivity(i2);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Data Pengunjung Masuk", Toast.LENGTH_LONG).show();
                break;

            case R.id.button_masuk_wisata :
                Intent i3 = new Intent(this, MainActivity3.class);
                startActivity(i3);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Masuk Wisata", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_masuk_user :
                Intent i4 = new Intent(this, MainActivity4.class);
                startActivity(i4);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Masuk Pengguna", Toast.LENGTH_LONG).show();
                break;
            case R.id.navi :
                Intent i5 = new Intent(this, MainActivityNavi.class);
                startActivity(i5);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                Toast.makeText(getApplicationContext(), "Masuk Pengguna", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
