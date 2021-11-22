package com.example.plant01.store;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.plant01.R;
import com.google.android.material.tabs.TabLayout;

public class SubActivity extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity_sub);

        // 뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.ViewPager);
        fragmentPagerAdapter = new store_categoryadapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.tabla);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
