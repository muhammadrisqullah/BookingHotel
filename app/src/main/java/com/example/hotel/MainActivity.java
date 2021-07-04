package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnPesan,btnFas,btnLok,btnHis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPesan = findViewById(R.id.btnBooking);
        btnFas = findViewById(R.id.btnFasilitas);
        btnLok = findViewById(R.id.btnLocation);
        btnHis = findViewById(R.id.btnHistory);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pesan.class);
                startActivity(intent);
            }
        });
        btnFas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this,Fasilitas.class);
                startActivity(intent);
            }
        });
        btnLok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this,Lokasi.class);
                startActivity(intent);
            }
        });
        btnHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });

    }
}