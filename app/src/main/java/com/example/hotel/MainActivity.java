package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //deklarasi variabel button
    Button btnPesan,btnFas,btnLok,btnHis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //memberi nilai variabel yang sudah dibuat
        btnPesan = findViewById(R.id.btnBooking);
        btnFas = findViewById(R.id.btnFasilitas);
        btnLok = findViewById(R.id.btnLocation);
        btnHis = findViewById(R.id.btnHistory);
        //membuat event click button pesan
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method untuk memangil activity pesan
                Intent intent = new Intent(MainActivity.this,Pesan.class);
                startActivity(intent);
            }
        });
        //membuat event click button fasilitas
        btnFas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method untuk memangil activity fasilitas
                Intent  intent = new Intent(MainActivity.this,Fasilitas.class);
                startActivity(intent);
            }
        });
        //membuat event click button lokasi
        btnLok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method untuk memangil activity lokasi
                Intent  intent = new Intent(MainActivity.this,Lokasi.class);
                startActivity(intent);
            }
        });
        //membuat event click button history
        btnHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method untuk memangil activity history
                Intent  intent = new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });

    }
}