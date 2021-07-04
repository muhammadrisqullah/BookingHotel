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
        nm = findViewById(R.id.etNama);
        nt = findViewById(R.id.etNt);
        lm = findViewById(R.id.etLm);
        hg = findViewById(R.id.tvHarga);
        btnPesan =findViewById(R.id.btnPesan);
        radioGroup = findViewById(R.id.radioGroup);


        database = FirebaseDatabase.getInstance().getReference();

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                jenis_kamar = radioButton.getText().toString();
                if (jenis_kamar.toString().equals("Single Bed"))
                {
                    String i = "250000";
                    String l = lm.getText().toString();
                    float i_as_a_float = Float.parseFloat(i);
                    float lm_as_a_float = Float.parseFloat(l);
                    float test = i_as_a_float * lm_as_a_float;
                    harga = Float.toString(test);
                }
                else
                {
                    String i = "500000";
                    String l = lm.getText().toString();
                    float i_as_a_float = Float.parseFloat(i);
                    float lm_as_a_float = Float.parseFloat(l);
                    float test = i_as_a_float * lm_as_a_float;
                    harga = Float.toString(test);
                }
                if (!(nm.getText().toString().isEmpty() && !(nt.getText().toString().isEmpty()   )&&(lm.getText().toString().isEmpty()) ))
                {
                    nama = nm.getText().toString();
                    telpon = nt.getText().toString();
                    lamamenginap = lm.getText().toString();
                    submitPesanan(new Pesanan(nama,telpon,lamamenginap,jenis_kamar,harga));
                }
                hg.setText(harga +" Rupiah");

            }
        });
    }
    private void  submitPesanan(Pesanan psn)
    {
        database.child("Pesanan").push().setValue(psn).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(Pesan.this,"Kamar Berhasil Dipesan",Toast.LENGTH_SHORT).show();
            }
        });
    }
}