package com.example.hotel.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Lihat_data;
import com.example.hotel.MainActivity;
import com.example.hotel.R;
import com.example.hotel.database.Pesanan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adp_Pesan extends RecyclerView.Adapter<adp_Pesan.ViewHolder> {
    private ArrayList<Pesanan> daftarPesanan;
    private Context context;
    private DatabaseReference databaseReference;

    public adp_Pesan(ArrayList<Pesanan> daftarPesanan, Context context) {
        this.daftarPesanan = daftarPesanan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull  adp_Pesan.ViewHolder holder, int position) {

        String kode, nama ,telpon,lm,hrg,jk;

        nama = daftarPesanan.get(position).getNama();
        kode = daftarPesanan.get(position).getKode();
        telpon = daftarPesanan.get(position).getNomor_telepon();
        lm = daftarPesanan.get(position).getLama_menginap();
        hrg = daftarPesanan.get(position).getHarga();
        jk = daftarPesanan.get(position).getJenis_Kamar();

        holder.tvnama.setText(nama);
        holder.tvnama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case  R.id.mnHapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                                alertDlg.setTitle("Yakin pesanan " + nama + " akan dihapus?");
                                alertDlg.setMessage("Tekan 'ya' untuk menghapus").setCancelable(false).setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DeleteData(kode);
                                        Toast.makeText(v.getContext(), "Pesanan " + nama + " Berhasil dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                                        v.getContext().startActivity(intent);
                                    }
                                })
                                        .setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog aDlg = alertDlg.create();
                                aDlg.show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        holder.tvnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Kunci1", nama);
                bundle.putString("Kunci2", telpon);
                bundle.putString("Kunci3", lm);
                bundle.putString("Kunci4", jk);
                bundle.putString("Kunci5", hrg);
                Intent intent = new Intent(v.getContext(),Lihat_data.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return daftarPesanan.size();
    }
    public void DeleteData(String kode){
        if(databaseReference != null){
            databaseReference.child("Pesanan").child(kode).removeValue();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama;
        ViewHolder(View v){
            super(v);
            tvnama = (TextView) v.findViewById(R.id.tv_nama);
            databaseReference= FirebaseDatabase.getInstance().getReference();
        }
    }
}
