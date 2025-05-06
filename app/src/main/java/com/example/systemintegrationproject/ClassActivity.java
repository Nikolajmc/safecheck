package com.example.systemintegrationproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClassActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnProfile;
    Button btnOpenClass;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnOpenClass = findViewById(R.id.btnOpenClass);
        btnProfile = (ImageButton) findViewById(R.id.btnProfile);

        btnOpenClass.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnOpenClass) {
            i = new Intent(this, AttendanceActivity.class);
            startActivity(i);
        }

        if (view == btnProfile) {
            i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
    }
}