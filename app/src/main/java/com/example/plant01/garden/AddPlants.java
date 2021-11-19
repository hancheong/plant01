package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plant01.R;
import com.example.plant01.home.Home;

public class AddPlants extends AppCompatActivity {

    EditText etName, etLocation, etDate;
    TextView tvTitle;
    Button btnBack, btnSave;
    View.OnClickListener cl;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        etName = (EditText) findViewById(R.id.etName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etDate = (EditText) findViewById(R.id.etDate);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (Button) findViewById(R.id.btnBack);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in = new Intent(AddPlants.this, Home.class);
            }
        };
        btnSave.setOnClickListener(cl);
        btnBack.setOnClickListener(cl);
    }
}