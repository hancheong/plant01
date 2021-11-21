package com.example.plant01.postpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.example.plant01.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    //    Button button;
//    View.OnClickListener cl;
//    Intent intent;
    TabLayout tabLayout;
    ViewPager2 pager2;
    post_FragmentAdapter adapter;
    //    FloatingActionButton floatingActionButton;
    public static Stack<Fragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.post_viewpage);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new post_FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);




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
//        intent = getIntent();
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        cl = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), WritePost.class);
//                startActivity(intent);
//            }
//        };
//        floatingActionButton.setOnClickListener(cl);

    }

}