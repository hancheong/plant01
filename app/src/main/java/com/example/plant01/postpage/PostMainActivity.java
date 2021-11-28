package com.example.plant01.postpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.plant01.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Stack;

public class PostMainActivity extends Fragment {

    public PostMainActivity() {
    }

    TabLayout tabLayout;
    ViewPager2 pager2;
    post_FragmentAdapter adapter;
    public static Stack<Fragment> fragmentStack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.post_main_fragment, container, false);


        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

        @Override
    public void onStart() {
        super.onStart();
            tabLayout = getView().findViewById(R.id.tab_layout);
            //게시글 담을 페이지
            pager2 = getView().findViewById(R.id.post_viewpage);
            //FragmentAdapter에서 카테고리 선택하여 페이지 이동
            FragmentManager fm = getFragmentManager();
            adapter = new post_FragmentAdapter(fm, getActivity().getLifecycle());
            pager2.setAdapter(adapter);
            //카테고리 선택

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    pager2.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            //카테고리 선택할 때마다 페이지 이동
            pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    tabLayout.selectTab(tabLayout.getTabAt(position));
                }
            });

    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
}

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //post_activitiy_main에서 상단 카테고리 목록
//
//}