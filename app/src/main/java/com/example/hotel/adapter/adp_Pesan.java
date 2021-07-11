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
    //line 30-32 mendeklarasikan variabel
    private ArrayList<Pesanan> daftarPesanan;
    private Context context;
    private DatabaseReference databaseReference;

    //konstruktor adapter pesan
    public adp_Pesan(ArrayList<Pesanan> daftarPesanan, Context context) {
        this.daftarPesanan = daftarPesanan;
        this.context = context;
    }

    @NonNull
    @Override
    //method saat membuat view holder
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan,parent,false);
        ViewHolder vh = new ViewHolder(v);
        //menampilkan viewholder
        return vh;
    }

    @Override
    //method untuk mengisikan ke dalam pesannan
    public void onBindViewHolder(@NonNull  adp_Pesan.ViewHolder holder, int position) {

        //mendeklarasikan variabel
        String kode, nama ,telpon,lm,hrg,jk;

        //memberikan nilai pada variabel
        nama = daftarPesanan.get(position).getNama();
        kode = daftarPesanan.get(position).getKode();
        telpon = daftarPesanan.get(position).getNomor_telepon();
        lm = daftarPesanan.get(position).getLama_menginap();
        hrg = daftarPesanan.get(position).getHarga();
        jk = daftarPesanan.get(position).getJenis_Kamar();

        holder.tvnama.setText(nama);
        //membuat event click lama item
        holder.tvnama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                //menampilkan popup menu
                popupMenu.inflate(R.menu.menu);
                //memberi event pada popupmenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //membuat menu pilihan
                        switch (item.getItemId()){
                            //pilihan untuk menghapus
                            case  R.id.mnHapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                                //memebrikan pesan konfirmasi penghapusan
                                alertDlg.setTitle("Yakin pesanan " + nama + " akan dihapus?");
                                //membuat kondisi jika ya
                                alertDlg.setMessage("Tekan 'ya' untuk menghapus").setCancelable(false).setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //memangil method delete data
                                        DeleteData(kode);
                                        //memberikan pesan data berhasil dihapus
                                        Toast.makeText(v.getContext(), "Pesanan " + nama + " Berhasil dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                                        //membuka activity mainactivity
                                        v.getContext().startActivity(intent);
                                    }
                                })
                                        //membuat kondisi jika tidak
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
                //menampilkan popupmenu
                popupMenu.show();
                return true;
            }
        });
        //membuat event click item
        holder.tvnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //membuat bundle
                Bundle bundle = new Bundle();
                //memasukan data-data kedalam bundle
                bundle.putString("Kunci1", nama);
                bundle.putString("Kunci2", telpon);
                bundle.putString("Kunci3", lm);
                bundle.putString("Kunci4", jk);
                bundle.putString("Kunci5", hrg);
                Intent intent = new Intent(v.getContext(),Lihat_data.class);
                //memasukan bundle ke intent
                intent.putExtras(bundle);
                //membuka activity lihat data
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    //method untuk menghitung banyak item
    public int getItemCount() {
        return daftarPesanan.size();
    }
    //method untuk menhapus data
    public void DeleteData(String kode){
        //membuat kondisi databaserefence tidak null
        if(databaseReference != null){
            //menghapus data
            databaseReference.child("Pesanan").child(kode).removeValue();
        }
    }
    //class untuk mengisi data item pesannan
    public class ViewHolder extends RecyclerView.ViewHolder {
        //deklarasi variabel
        TextView tvnama;
        ViewHolder(View v){
            super(v);
            //mengisi data ke item pesanan
            tvnama = (TextView) v.findViewById(R.id.tv_nama);
            //mengambil referensi ke firebase database
            databaseReference= FirebaseDatabase.getInstance().getReference();
        }
    }
}
