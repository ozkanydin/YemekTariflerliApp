package com.example.yemektarifleriapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class Kaydedilenler extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> yemek_isimFB;
    ArrayList<String> yemek_turFB;
    ArrayList<String> yemek_icerikFB;
    ArrayList<String> yemek_hazirFB;
    ArrayList<String> yemek_paylasanFB;
    ArrayList<String> yemek_fotoFB;
    ArrayList<String> yemek_tarihFB;
    Adapter adapter;
    Button kaydet;
    String y_isim;
    String y_tur ;
    String y_icerik;
    String y_hazir ;
    String y_paylasan ;
    String y_foto ;
    TextView yemek_input;
    Button listele;
    String s1;
    String yazi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydedilenler);
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

        listele = findViewById(R.id.listele);
        yemek_input = findViewById(R.id.yemek_input);


        final String yazi = yemek_input.getText().toString();

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("asd", MODE_PRIVATE).edit();
                editor.putString("name", yemek_input.getText().toString());
                editor.commit();

                yemek_input.setText("");
                Intent intentt = new Intent(getApplicationContext(),Kaydedilenler.class);
                startActivity(intentt);



            }
        });

        SharedPreferences prefs = getSharedPreferences("asd", MODE_PRIVATE);
        String s1 = prefs.getString("name", "No name defined");
        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();





// We can then use the data

        firebaseFirestore = FirebaseFirestore.getInstance();

        yemek_isimFB = new ArrayList<>();
        yemek_turFB = new ArrayList<>();
        yemek_icerikFB = new ArrayList<>();
        yemek_hazirFB = new ArrayList<>();
        yemek_paylasanFB = new ArrayList<>();
        yemek_fotoFB = new ArrayList<>();
        yemek_tarihFB= new ArrayList<>();


        getDataFromFirestore(s1);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(yemek_isimFB,yemek_turFB,yemek_icerikFB,yemek_hazirFB,yemek_paylasanFB,
                yemek_fotoFB,yemek_tarihFB);
        recyclerView.setAdapter(adapter);

    }
    public void getDataFromFirestore(String s2){
        CollectionReference collectionReference = firebaseFirestore.collection("yemek_tarifleri");
        collectionReference.whereEqualTo("yemek_tur",s2).addSnapshotListener(new EventListener<QuerySnapshot>() {
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





                        adapter.notifyDataSetChanged();



                    }
                }
            }
        });

    }
}
