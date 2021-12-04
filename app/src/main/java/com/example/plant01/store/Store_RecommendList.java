package com.example.plant01.store;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.example.plant01.home.bell;
import com.example.plant01.usersetting.UserSetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.local.QueryResult;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store_RecommendList extends AppCompatActivity {

    ImageButton store_back, img1, img2, img3, img4, img5;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private store_GoodsAdapter2 store_goodsAdapter2;
    private List <StoreGoods> list;
    private ImageButton store_resultback;
    private store_GoodsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    TextView store1, title1, review1, price1;
    TextView store2, title2, review2, price2;
    TextView store3, title3, review3, price3;
    TextView store4, title4, review4, price4;
    TextView store5, title5, review5, price5;

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

        recyclerView = findViewById(R.id.store_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        store_goodsAdapter2 = new store_GoodsAdapter2(this, list);
        recyclerView.setAdapter(store_goodsAdapter2);

        showData();

    }

    private void showData() {

        // 상품 데이터 객체 생성
        db.collection("StoreGoods").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot : task.getResult()){
                            StoreGoods storeGoods = new StoreGoods(snapshot.getString("goodsImg"),snapshot.getString("StoreName"),snapshot.getString("GoodsTitle"),snapshot.getString("GoodsReview"),snapshot.getString("GoodsPrice"));
                            list.add(storeGoods);
                        }
                        store_goodsAdapter2.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Store_RecommendList.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}







