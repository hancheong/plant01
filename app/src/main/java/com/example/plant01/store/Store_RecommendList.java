package com.example.plant01.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plant01.R;

public class Store_RecommendList extends AppCompatActivity {

    private ImageButton store_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // 뒤로가기 버튼 누를 시 추천상품 페이지로 돌아감
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_recommend_list);

        store_back = (ImageButton) findViewById(R.id.store_back);
        store_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}