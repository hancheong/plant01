package com.example.plant01.postpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.example.plant01.home.bell;
import com.example.plant01.usersetting.UserSetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Stack;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostMainActivity extends Fragment {

    public PostMainActivity() {
    }

    TabLayout tabLayout;
    ViewPager2 pager2;
    post_FragmentAdapter adapter;
    public static Stack<Fragment> fragmentStack;
    private View.OnClickListener cl;

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
//            fm.beginTransaction().addToBackStack(null).commit();
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
            //드로워
            final DrawerLayout drawerLayout = (DrawerLayout) getView().findViewById(R.id.postdrawerLayout);
            NavigationView navigationView = getView().findViewById(R.id.post_drawer);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.btn_notice:
                            Intent intent1 = new Intent(getActivity(), PostNotice.class);
                            startActivity(intent1);
                            break;
//                        case R.id.btn_post:
//                            Intent intent2 = new Intent(getActivity(), bell.class);
//                            startActivity(intent2);
//                            break;
                    }
                    return true;
                }
            });
            //햄버거 버튼 이미지 클릭시 작동
            cl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.more: //햄버거 버튼
                            drawerLayout.openDrawer(GravityCompat.START);
                    }

                }
            };
            getView().findViewById(R.id.more).setOnClickListener(cl);


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
