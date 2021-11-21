package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plant01.R;

public class AddPlants extends AppCompatActivity {

    EditText etName, etLocation, etDate;
    TextView tvTitle;
    Button btnBack, btnSave;
    ImageView ivPlants;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        etName = (EditText) findViewById(R.id.etName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etDate = (EditText) findViewById(R.id.etDate);

        ivPlants = (ImageView) findViewById(R.id.ivPlants);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (Button) findViewById(R.id.btnBack);


        btnBack.setOnClickListener(new View.OnClickListener() {//뒤로가기 버튼
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getApplicationContext(), MyGarden.class);
                   startActivity(intent);
               }
           });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPlants.this, MyPlants.class );//내 식물 화면으로 전환
            }
        };
        btnSave.setOnClickListener(cl);
    }
}