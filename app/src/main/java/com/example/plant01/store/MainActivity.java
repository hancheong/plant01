package com.example.plant01.store;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;


import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference();
    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment HomeFragment;
    private home home;
    private plant plant;
    private board board;
    private store store;
    private store_category store_category;
    private FragmentPagerAdapter fragmentPagerAdapter;


    Button bt1, bt2;
    EditText name, title, review, price, inp;
    TextView result1, result2, result3, result4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity_main);


        bt1 = findViewById(R.id.button);
        name = findViewById(R.id.StoreName);
        title = findViewById(R.id.GoodsPageTitle);
        review = findViewById(R.id.ReviewCount);
        price = findViewById(R.id.GoodsPrice);

        bt2 = findViewById(R.id.button2);
        inp = findViewById(R.id.inp);
        result1 = findViewById(R.id.textView);
        result2 = findViewById(R.id.textView2);
        result3 = findViewById(R.id.textView3);
        result4 = findViewById(R.id.textView4);
/*
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addgoods(name.getText().toString(), title.getText().toString(), review.getText().toString(), price.getText().toString());
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = String.valueOf(inp.getText());
                getgoods(a);
            }
        });

        public void addgoods(String name, String title, String review, String price) {
            goods goods;
            goods = new goods(name, title, review, price);
            databaseReference.child("goods").child(name).setValue(goods);
        }

        public void getgoods(String s) {
            DatabaseReference data = databaseReference.child("goods").child(s);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    goods value = snapshot.getValue(goods.class);
                    result1.setText(value.name);
                    result2.setText(value.title);
                    result3.setText(value.review);
                    result4.setText(value.price);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
*/



        bottomNavigationView = findViewById(R.id.Navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.NaviHome:
                        setFrag(0);
                        break;
                    case R.id.NaviPlant:
                        setFrag(1);
                        break;
                    case R.id.NaviBoard:
                        setFrag(2);
                        break;
                    case R.id.NaviStore:
                        setFrag(3);
                        break;


                }
                return true;
            }
        });
//        home = new home();
        HomeFragment = new HomeFragment();
        plant = new plant();
        board = new board();
        store = new store();
        store_category = new store_category();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택

    }

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.Frame, HomeFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.Frame, plant);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.Frame, board);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.Frame, store_category);
                ft.commit();
                break;



        }





    }

}