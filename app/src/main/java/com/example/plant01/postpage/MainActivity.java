package com.example.plant01.postpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plant01.R;

//게시판 페이지 123123
public class MainActivity extends AppCompatActivity {

    Button button;
    View.OnClickListener cl;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostMain.class);
                startActivity(intent);
            }
        };
        button.setOnClickListener(cl);
    }
}