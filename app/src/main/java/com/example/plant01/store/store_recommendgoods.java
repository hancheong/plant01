package com.example.plant01.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plant01.R;


public class store_recommendgoods extends Fragment {

    private View view;
    private ImageButton btn_recommend;

    public static store_recommendgoods newInstance() {
        store_recommendgoods storeRecommendgoods = new store_recommendgoods();
        return storeRecommendgoods;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_recommendgoods, container, false);


        btn_recommend = view.findViewById(R.id.store_recommend_btn);
        btn_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Store_RecommendList.class);
                startActivity(intent);
            }
        });
        return view;
    }
}