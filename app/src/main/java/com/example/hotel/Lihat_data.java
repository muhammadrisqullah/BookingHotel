package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Lihat_data extends AppCompatActivity {
    //line 13-15 mendeklarasikan variabel
    private TextView tvnm,tvtl,tvlm,tvjk,tvhg;
    private String nama,telpon,lama_menginap,jenis_kamar,harga;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        //memberi nilai variabel yang sudah dibuat
        tvnm = findViewById(R.id.tvnama);
        tvtl = findViewById(R.id.tvTelpon);
        tvlm = findViewById(R.id.tvLamaM);
        tvjk = findViewById(R.id.tvJenisK);
        tvhg =  findViewById(R.id.tvHargaPsn);
        //mengambil referensi ke firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //mendeklarasikan bundle
        Bundle bundle = this.getIntent().getExtras();
        //memasukan nilai dari bundle ke string
        nama = bundle.getString("Kunci1");
        telpon = bundle.getString("Kunci2");
        lama_menginap = bundle.getString("Kunci3");
        jenis_kamar = bundle.getString("Kunci4");
        harga = bundle.getString("Kunci5");

        //mengset text sesuai dengan nilai dari bundle
        tvnm.setText(nama);
        tvtl.setText(telpon);
        tvlm.setText(lama_menginap +" Hari");
        tvjk.setText(jenis_kamar);
        tvhg.setText(harga + " Rupiah");

    }
}