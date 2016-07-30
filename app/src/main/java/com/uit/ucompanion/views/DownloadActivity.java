package com.uit.ucompanion.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.uit.ucompanion.R;

import java.io.File;
import java.io.IOException;

public class DownloadActivity extends AppCompatActivity {

    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btnDownload = (Button) findViewById(R.id.btnDownload);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getSharedPreferences("uCompanionSP", MODE_PRIVATE);
        String username = prefs.getString("username", "");

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference ref = storage.getReferenceFromUrl("gs://uit-companion.appspot.com");

                final StorageReference imgRef = ref.child("Lectures/MM/firebase-logo.png");

                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println(uri.toString());
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(uri.toString()));
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
