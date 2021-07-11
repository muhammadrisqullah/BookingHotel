package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.database.Pesanan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pesan extends AppCompatActivity {
    //line 21-27 mendeklarasikan variabel
    EditText nm,nt,lm;
    TextView hg;
    Button btnPesan;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseReference database;
    String nama,telpon,lamamenginap,jenis_kamar,harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        //memberi nilai variabel yang sudah dibuat
        nm = findViewById(R.id.etNama);
        nt = findViewById(R.id.etNt);
        lm = findViewById(R.id.etLm);
        hg = findViewById(R.id.tvHarga);
        btnPesan =findViewById(R.id.btnPesan);
        radioGroup = findViewById(R.id.radioGroup);


        //mengambil referensi ke firebase database
        database = FirebaseDatabase.getInstance().getReference();
        //membuat event click button pesan
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deklarasi int untuk menyimpan nilai radio button
                int selectedId = radioGroup.getCheckedRadioButtonId();
                //memeberi nilai variabel radio button
                radioButton = findViewById(selectedId);
                //memberi nilai variabel jenis kamar yaitu text string dari radio button
                jenis_kamar = radioButton.getText().toString();
                //membuat kondisi jika kamar yang dipilih yaitu single bed
                if (jenis_kamar.toString().equals("Single Bed"))
                {
                    //deklarasi string i dan l untuk menentukan total harga
                    String i = "250000";
                    String l = lm.getText().toString();
                    //mengubah nilai string i ke tipe data float
                    float i_as_a_float = Float.parseFloat(i);
                    //mengubah nilai string l ke tipe data float
                    float lm_as_a_float = Float.parseFloat(l);
                    //deklarasi variabil test yaitu nilai perkalian dari lama menginap dan variabel i
                    float test = i_as_a_float * lm_as_a_float;
                    //memberi nilai harga yaitu seharga test
                    harga = Float.toString(test);
                }
                //kondisi jika kamar yg dipilih selain single bed(yaitu twin bed)
                else
                {
                    //deklarasi string i dan l untuk menentukan total harga
                    String i = "500000";
                    String l = lm.getText().toString();
                    //mengubah nilai string i ke tipe data float
                    float i_as_a_float = Float.parseFloat(i);
                    //mengubah nilai string l ke tipe data float
                    float lm_as_a_float = Float.parseFloat(l);
                    //deklarasi variabil test yaitu nilai perkalian dari lama menginap dan variabel i
                    float test = i_as_a_float * lm_as_a_float;
                    //memberi nilai harga yaitu seharga test
                    harga = Float.toString(test);
                }
                //membuat kondisi jika semua data telah diisi
                if (!(nm.getText().toString().isEmpty() && !(nt.getText().toString().isEmpty()   )&&(lm.getText().toString().isEmpty()) ))
                {
                    //memberi nilai variabel
                    nama = nm.getText().toString();
                    telpon = nt.getText().toString();
                    lamamenginap = lm.getText().toString();
                    //memangil method submitpesannan
                    submitPesanan(new Pesanan(nama,telpon,lamamenginap,jenis_kamar,harga));
                }
                //menset text untuk menampilkan harga yg telah dihitung
                hg.setText(harga +" Rupiah");

            }
        });
    }
    //membuat method untuk menambahkan data ke database
    private void  submitPesanan(Pesanan psn)
    {
        //menambahkan data ke database
        database.child("Pesanan").push().setValue(psn).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //menampilkan pesan text
                Toast.makeText(Pesan.this,"Kamar Berhasil Dipesan",Toast.LENGTH_SHORT).show();
            }
        });
    }
}