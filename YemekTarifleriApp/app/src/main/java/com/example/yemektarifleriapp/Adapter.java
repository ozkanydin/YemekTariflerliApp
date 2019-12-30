package com.example.yemektarifleriapp;

import android.content.Context;
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

public class Adapter extends RecyclerView.Adapter<Adapter.PostHolder> {
Item item;
    private Context mContext;


    public Adapter(ArrayList<String> yemek_isimad, ArrayList<String> yemek_turad, ArrayList<String> yemek_icerikad, ArrayList<String> yemek_hazirad, ArrayList<String> yemek_paylasanad, ArrayList<String> yemek_fotoad, ArrayList<String> yemek_tarihad) {
        this.yemek_isimad = yemek_isimad;
        this.yemek_turad = yemek_turad;
        this.yemek_icerikad = yemek_icerikad;
        this.yemek_hazirad = yemek_hazirad;
        this.yemek_paylasanad = yemek_paylasanad;
        this.yemek_fotoad = yemek_fotoad;
        this.yemek_tarihad = yemek_tarihad;
    }

    ArrayList<String> yemek_isimad;
    ArrayList<String> yemek_turad;
    ArrayList<String> yemek_icerikad;
    ArrayList<String> yemek_hazirad;
    ArrayList<String> yemek_paylasanad;
    ArrayList<String> yemek_fotoad;
    ArrayList<String> yemek_tarihad;



    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post,parent,false);


        return new PostHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.icerik.setText(yemek_icerikad.get(position));
        holder.isim.setText(yemek_isimad.get(position));
        holder.paylasan.setText(yemek_paylasanad.get(position));
        holder.hazir.setText(yemek_hazirad.get(position));
        holder.tur.setText(yemek_turad.get(position));
        Picasso.get().load(yemek_fotoad.get(position)).into(holder.resim);














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


        public PostHolder(@NonNull final View itemView) {
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
