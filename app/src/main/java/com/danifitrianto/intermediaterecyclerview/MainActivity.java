package com.danifitrianto.intermediaterecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.danifitrianto.intermediaterecyclerview.views.ControllerUserActivity;
import com.danifitrianto.intermediaterecyclerview.views.UserActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd,btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);

        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ControllerUserActivity.class);
            startActivity(i);
        });

        btnRead.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
        });
    }
}