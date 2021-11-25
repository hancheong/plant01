package com.example.plant01.store;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.plant01.R;
import com.google.android.material.tabs.TabLayout;


public class store_category extends Fragment {

    int i=0;
    ViewPager viewPager;
    private store category1;
    private store_recommendgoods category2;
    public store_category(){
    }
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.store_activity_sub, container, false);

        // 뷰페이저 세팅
        ViewPager viewPager = view.findViewById(R.id.ViewPager);
        fragmentPagerAdapter = new store_categoryadapter(getFragmentManager());

        TabLayout tabLayout = view.findViewById(R.id.tabla);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;

    }
}

