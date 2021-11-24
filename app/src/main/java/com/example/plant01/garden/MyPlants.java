package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;
import com.example.plant01.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPlants extends AppCompatActivity implements View.OnClickListener {

    TextView tvName, tvLocation, tvDate;
    Button btnBack;
    ImageView ivPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_my_plants);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String location = intent.getStringExtra("location");
        String date = intent.getStringExtra("date");

        tvName = (TextView) findViewById(R.id.tvName);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDate = (TextView) findViewById(R.id.tvDate);

        tvName.setText(name);
        tvLocation.setText(location);
        tvDate.setText(date);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name", tvName.getText().toString());
        intent.putExtra("location", tvLocation.getText().toString());
        intent.putExtra("date", tvDate.getText().toString());

        setResult(RESULT_OK, intent);
        finish();

    }
}