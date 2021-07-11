package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hotel.adapter.adp_Pesan;
import com.example.hotel.database.Pesanan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    //line 22-26 mendeklarasikan variabel
    private DatabaseReference databaseReference;
    private RecyclerView rcView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pesanan> dataPsn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //memberi nilai variabel recycler view
        rcView= (RecyclerView) findViewById(R.id.rv_utama);
        rcView.setHasFixedSize(true);
        //mendeklarasikan layout manager
        layoutManager = new LinearLayoutManager(this);
        //menset layoutmanager recyclerview
        rcView.setLayoutManager(layoutManager);


        //mengambil referensi ke firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //mengambil data Pesanan dari firebase realtime database
        databaseReference.child("Pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            //
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                dataPsn = new ArrayList<>();
                for (DataSnapshot daftarDS:snapshot.getChildren()){
                    Pesanan psn = daftarDS.getValue(Pesanan.class);
                    psn.setKode(daftarDS.getKey());
                    //menambahkan data ke dalam array list
                    dataPsn.add(psn);
                }
                //mendeklarasikan adapter
                adapter = new adp_Pesan(dataPsn,History.this);
                //mengisi recycler view
                rcView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //menampilkan error detail
                System.out.println(error.getDetails()+""+error.getMessage());
            }
        });


    }
}