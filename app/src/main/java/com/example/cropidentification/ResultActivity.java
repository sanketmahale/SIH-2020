package com.example.cropidentification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    TextView tvCropName, tvLocation;
    Button btnShare;
    ImageView ivCrop;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvCropName = findViewById(R.id.tvCropName);
        tvLocation = findViewById(R.id.tvLocation);
        btnShare = findViewById(R.id.btnShare);
        ivCrop = findViewById(R.id.ivCrop);
        final Intent a = getIntent();
        final Bundle extras = a.getExtras();
        if(getIntent().hasExtra("image")) {
            bitmap = BitmapFactory.decodeByteArray(
                    a.getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
            ivCrop.setImageBitmap(bitmap);
        }
        String result= extras.getString("crop");
        double lon = extras.getDouble("lon");
        double lat = extras.getDouble("lat");

        tvCropName.setText("Result:\n" + result);
        tvLocation.setText("Location:\n" + "Longitude: " + lon + "\tLatitude: " + lat);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f = new File(getExternalCacheDir(), "p1.png");
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Intent a = new Intent(Intent.ACTION_SEND);
                String msg = "Crop: " + extras.getString("crop") + "\nLocation:\n" + "Longitude:" +
                        extras.getDouble("lon") + " "
                        + "Latitude: " + extras.getDouble("lat");
                a.putExtra(Intent.EXTRA_TEXT, msg);
                a.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                a.setType("image/*");
                startActivity(a);

            }
        });
    }

//    public void shareImage(View v) {
//        File f = new File(uri.getPath());
//        Intent i = new Intent(Intent.ACTION_SEND);
//        Uri myUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName()+".provider", f);
//        i.setDataAndType(myUri, "image/*");
//        i.putExtra(Intent.EXTRA_STREAM, myUri);
//        startActivity(i);
//    }
}
