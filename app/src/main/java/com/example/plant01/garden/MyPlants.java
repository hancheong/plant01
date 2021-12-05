package com.example.plant01.garden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plant01.R;

public class MyPlants extends AppCompatActivity {

    TextView tvName, tvLocation, tvDate;
    ImageView ivPlants;
    String name, location, date, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_my_plants);

        tvName = (TextView) findViewById(R.id.tvName);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDate = (TextView) findViewById(R.id.tvDate);
        ivPlants = (ImageView) findViewById(R.id.ivPlants);

        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvLocation.setText(intent.getStringExtra("location"));
        tvDate.setText(intent.getStringExtra("date"));
        ivPlants.setImageURI(Uri.parse(intent.getStringExtra("profile")));



    }

}