package com.example.systemintegrationproject;

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

public class AttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnBack2;
    Button btnStart;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attendance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnStart = findViewById(R.id.btnStart);
        btnBack2 = (ImageButton) findViewById(R.id.btnBack2);

        btnStart.setOnClickListener(this);
        btnBack2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            i = new Intent(this, MapsActivity.class);
            startActivity(i);
        }

        if (view == btnBack2) {
            i = new Intent(this, ClassActivity.class);
            startActivity(i);
        }
    }
}