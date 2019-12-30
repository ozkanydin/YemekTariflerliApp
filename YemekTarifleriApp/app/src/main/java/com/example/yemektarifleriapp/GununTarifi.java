package com.example.yemektarifleriapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class GununTarifi extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> yemek_isimFB;
    ArrayList<String> yemek_turFB;
    ArrayList<String> yemek_icerikFB;
    ArrayList<String> yemek_hazirFB;
    ArrayList<String> yemek_paylasanFB;
    ArrayList<String> yemek_fotoFB;
    ArrayList<String> yemek_tarihFB;
    Adapterr adapterr;
    Button kaydet;
    String y_isim;
    String y_tur ;
    String y_icerik;
    String y_hazir ;
    String y_paylasan ;
    String y_foto ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunun_tarifi);

        TextView textView = findViewById(R.id.textView);

        final Intent intent = new Intent(getApplicationContext(), Ekle.class);
        Button git = (Button) findViewById(R.id.git);
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        final Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
        final Intent intent2 = new Intent(getApplicationContext(), Kaydedilenler.class);
        Button saved = (Button) findViewById(R.id.saved);
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
        final Intent intent3 = new Intent(getApplicationContext(), GununTarifi.class);
        Button daily = (Button) findViewById(R.id.daily);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });


        firebaseFirestore = FirebaseFirestore.getInstance();

        yemek_isimFB = new ArrayList<>();
        yemek_turFB = new ArrayList<>();
        yemek_icerikFB = new ArrayList<>();
        yemek_hazirFB = new ArrayList<>();
        yemek_paylasanFB = new ArrayList<>();
        yemek_fotoFB = new ArrayList<>();
        yemek_tarihFB= new ArrayList<>();


        getDataFromFirestore();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterr = new Adapterr(yemek_isimFB,yemek_turFB,yemek_icerikFB,yemek_hazirFB,yemek_paylasanFB,
                yemek_fotoFB,yemek_tarihFB);
        recyclerView.setAdapter(adapterr);

    }
    public void getDataFromFirestore(){
        CollectionReference collectionReference = firebaseFirestore.collection("yemek_tarifleri");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                if(queryDocumentSnapshots != null){
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        Map<String,Object> data = snapshot.getData();

                        y_isim = (String) data.get("yemek_isim");
                        y_tur = (String) data.get("yemek_tur");
                        y_icerik = (String) data.get("yemek_icerik");
                        y_hazir = (String) data.get("yemek_hazir");
                        y_paylasan = (String) data.get("y_paylasan");
                        y_foto = (String) data.get("yemek_foto");

                        yemek_isimFB.add(y_isim);
                        yemek_turFB.add(y_tur);
                        yemek_icerikFB.add(y_icerik);
                        yemek_hazirFB.add(y_hazir);
                        yemek_paylasanFB.add(y_paylasan);
                        yemek_fotoFB.add(y_foto);
                        adapterr.notifyDataSetChanged();









                    }
                }
            }
        });

    }
}
