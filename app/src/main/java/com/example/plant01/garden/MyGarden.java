package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;


public class MyGarden extends AppCompatActivity {

    TextView tvGarden;
    ImageButton ibnBack;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_my_garden);

        tvGarden = (TextView) findViewById(R.id.tvGarden);
        ibnBack = (ImageButton) findViewById(R.id.ibnBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        ibnBack.setOnClickListener(new View.OnClickListener() {//뒤로가기 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeFragment.class);
                startActivity(intent);
            }
        });
    }
}