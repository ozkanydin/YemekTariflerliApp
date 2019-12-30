package com.example.yemektarifleriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Ekle extends AppCompatActivity {


    private ImageButton fotoekle;
    EditText yemek_isim;
    EditText yemek_tur;
    EditText yemek_icerik;
    EditText yemek_hazir;
    EditText yemek_paylasan;
    private FirebaseFirestore firebaseFirestore;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);
        yemek_isim = (EditText) findViewById(R.id.yemek_isim);
        yemek_tur = (EditText) findViewById(R.id.yemek_tur);
        yemek_icerik = (EditText) findViewById(R.id.yemek_icerik);
        yemek_hazir = (EditText) findViewById(R.id.yemek_hazir);
        yemek_paylasan = (EditText) findViewById(R.id.yemek_paylasan);
        Button ekle = (Button) findViewById(R.id.ekle);
        fotoekle = (ImageButton) findViewById(R.id.fotoekle);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseFirestore = firebaseFirestore.getInstance();



        fotoekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayit();
            }
        });




    }

    private void kayit() {

        final String yemek_isimm = yemek_isim.getText().toString();
        final String yemek_turr = yemek_tur.getText().toString();
        final String yemek_icerikk = yemek_icerik.getText().toString();
        final String yemek_hazirr = yemek_hazir.getText().toString();
        final String yemek_paylasann = yemek_paylasan.getText().toString();
        final String yemek_tarih;



        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Yükleniyor...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+yemek_isimm);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Ekle.this, "Yüklendi", Toast.LENGTH_SHORT).show();

                            StorageReference newReference = FirebaseStorage.getInstance().getReference("images/"+yemek_isimm);
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUrl = uri.toString();

                                    HashMap<String,Object>postData = new HashMap<>();
                                    postData.put("yemek_isim",yemek_isimm);
                                    postData.put("yemek_tur",yemek_turr);
                                    postData.put("yemek_icerik",yemek_icerikk);
                                    postData.put("yemek_paylasan",yemek_paylasann);
                                    postData.put("yemek_hazir",yemek_hazirr);
                                    postData.put("yemek_tarih", FieldValue.serverTimestamp());
                                    postData.put("yemek_foto",downloadUrl);

                                    firebaseFirestore.collection("yemek_tarifleri").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {

                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                                }
                            });





                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Ekle.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Yüklendi "+(int)progress+"%");
                        }
                    });
        }

    }
    //Telefondan resim seçmeyi sağlayan metod
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
