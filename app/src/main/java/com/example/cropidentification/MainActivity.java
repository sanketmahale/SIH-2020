package com.example.cropidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    TextView tvAppName;
    Button btnGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera = findViewById(R.id.btnCamera);
        btnGoogleMap=findViewById(R.id.btnGoogleMap);

        tvAppName = findViewById(R.id.tvAppName);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(a);
            }
        });

//        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent g=new Intent(MainActivity.this,MapsActivity.class);
//                startActivity(g);
//            }
//        });
    }
}

