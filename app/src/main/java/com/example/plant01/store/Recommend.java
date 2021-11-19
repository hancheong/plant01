package com.example.plant01.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.plant01.R;

public class Recommend extends AppCompatActivity {

    private Button btn_recommend;
    private FragmentPagerAdapter fragmentPagerAdapter;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_recommendgoods);

        btn_recommend = (Button) findViewById(R.id.store_recommend_btn);
        btn_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recommend.this, Store_RecommendList.class);
                startActivity(intent); // 추천상품 목록 이동
            }
        });

    }}