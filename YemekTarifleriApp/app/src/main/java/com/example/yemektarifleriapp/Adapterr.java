package com.example.yemektarifleriapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Adapterr extends RecyclerView.Adapter<Adapterr.PostHolder> {
    ArrayList<String> yemek_isimad;
    ArrayList<String> yemek_turad;
    ArrayList<String> yemek_icerikad;
    ArrayList<String> yemek_hazirad;
    ArrayList<String> yemek_paylasanad;
    ArrayList<String> yemek_fotoad;
    ArrayList<String> yemek_tarihad;
    Integer sayixx = 0;


    public Adapterr(ArrayList<String> yemek_isimad, ArrayList<String> yemek_turad, ArrayList<String> yemek_icerikad, ArrayList<String> yemek_hazirad, ArrayList<String> yemek_paylasanad, ArrayList<String> yemek_fotoad, ArrayList<String> yemek_tarihad) {
        this.yemek_isimad = yemek_isimad;
        this.yemek_turad = yemek_turad;
        this.yemek_icerikad = yemek_icerikad;
        this.yemek_hazirad = yemek_hazirad;
        this.yemek_paylasanad = yemek_paylasanad;
        this.yemek_fotoad = yemek_fotoad;
        this.yemek_tarihad = yemek_tarihad;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post,parent,false);


        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterr.PostHolder holder, int position) {
        Integer sayi  = yemek_isimad.size();
        Integer randomInt = ThreadLocalRandom.current().nextInt(1, sayi);

        if(sayixx == 0){
            holder.icerik.setText(yemek_icerikad.get(randomInt));
            holder.isim.setText(yemek_isimad.get(randomInt));
            holder.paylasan.setText(yemek_paylasanad.get(randomInt));
            holder.hazir.setText(yemek_hazirad.get(randomInt));
            holder.tur.setText(yemek_turad.get(randomInt));
            Picasso.get().load(yemek_fotoad.get(randomInt)).into(holder.resim);
            sayixx++;

        }














    }

    @Override
    public int getItemCount() {
        return yemek_isimad.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        ImageView resim;
        TextView isim;
        TextView tur;
        TextView paylasan;
        TextView hazir;
        TextView icerik;
        Button kaydet;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            resim = itemView.findViewById(R.id.rcyemek_foto);
            isim = itemView.findViewById(R.id.rcyemek_isim);
            tur = itemView.findViewById(R.id.rcyemek_tur);
            paylasan = itemView.findViewById(R.id.rcyemek_paylasan);
            hazir = itemView.findViewById(R.id.rcyemek_hazir);
            icerik = itemView.findViewById(R.id.rcyemek_icerik);
        }
    }
}
