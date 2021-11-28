package com.example.plant01.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plant01.R;


public class store extends Fragment {
    private View view;
    private TextView more;

    public static store newInstance() {
        store Store = new store();
        return Store;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_store, container, false);


        more = view.findViewById(R.id.store_more);
        more.setOnClickListener(new View.OnClickListener() { // 스토어 메인에서 추천상품 더보기 버튼을 누르면 추천상품 리스트로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Store_RecommendList.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
