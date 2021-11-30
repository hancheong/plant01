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

    private ImageButton store_back;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private store_GoodsAdapter store_goodsAdapter;
    private List <StoreGoods> list;

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
        store_goodsAdapter = new store_GoodsAdapter(this, list);
        recyclerView.setAdapter(store_goodsAdapter);

        showData();

    }

    private void showData() {

        store1 = (TextView) findViewById(R.id.recommend_store1);
        title1 = (TextView) findViewById(R.id.recommend_title1);
        review1 = (TextView) findViewById(R.id.recommend_review1);
        price1 = (TextView) findViewById(R.id.recommend_price1);

        store2 = (TextView) findViewById(R.id.recommend_store2);
        title2 = (TextView) findViewById(R.id.recommend_title2);
        review2 = (TextView) findViewById(R.id.recommend_review2);
        price2 = (TextView) findViewById(R.id.recommend_price2);

        store3 = (TextView) findViewById(R.id.recommend_store3);
        title3 = (TextView) findViewById(R.id.recommend_title3);
        review3 = (TextView) findViewById(R.id.recommend_review3);
        price3 = (TextView) findViewById(R.id.recommend_price3);

        store4 = (TextView) findViewById(R.id.recommend_store4);
        title4 = (TextView) findViewById(R.id.recommend_title4);
        review4 = (TextView) findViewById(R.id.recommend_review4);
        price4 = (TextView) findViewById(R.id.recommend_price4);

        store5 = (TextView) findViewById(R.id.recommend_store5);
        title5 = (TextView) findViewById(R.id.recommend_title5);
        review5 = (TextView) findViewById(R.id.recommend_review5);
        price5 = (TextView) findViewById(R.id.recommend_price5);

// 첫번째 상품
        DocumentReference goods1 = db.collection("StoreGoods").document("8hIJAMJMC9WA0sw7hKp8");
        goods1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String storeName1 = (String) document.get("StoreName");  // 스토어 이름 지정
                        store1.setText(storeName1);
                        String goodsTitle1 = (String) document.get("GoodsTitle"); // 상품 제목 지정
                        title1.setText(goodsTitle1);
                        String goodsReview1 = (String) document.get("GoodsReview"); // 상품 리뷰 지정
                        review1.setText(goodsReview1);
                        String goodsPrice1 = (String) document.get("GoodsPrice"); // 상품 가격 지정
                        price1.setText(goodsPrice1);
                    } else {
                        Log.d(TAG, "No such document"); // 문서를 못찾았을 경우
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException()); // 실패했을 경우. 이하 상품 코드 동일
                }
            }
        });
// 두번째 상품
        DocumentReference goods2 = db.collection("StoreGoods").document("brAE7c0BTuhQqInLY73s");
        goods2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String storeName2 = (String) document.get("StoreName");
                        store2.setText(storeName2);
                        String goodsTitle2 = (String) document.get("GoodsTitle");
                        title2.setText(goodsTitle2);
                        String goodsReview2 = (String) document.get("GoodsReview");
                        review2.setText(goodsReview2);
                        String goodsPrice2 = (String) document.get("GoodsPrice");
                        price2.setText(goodsPrice2);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
// 세번째 상품
        DocumentReference goods3 = db.collection("StoreGoods").document("ki4XlFCvbFUHyh92v0oT");
        goods3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String storeName3 = (String) document.get("StoreName");
                        store3.setText(storeName3);
                        String goodsTitle3 = (String) document.get("GoodsTitle");
                        title3.setText(goodsTitle3);
                        String goodsReview3 = (String) document.get("GoodsReview");
                        review3.setText(goodsReview3);
                        String goodsPrice3 = (String) document.get("GoodsPrice");
                        price3.setText(goodsPrice3);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
// 네번째 상품
        DocumentReference goods4 = db.collection("StoreGoods").document("p0hx6kpCb7wVorH4xKoK");
        goods4.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String storeName4 = (String) document.get("StoreName");
                        store4.setText(storeName4);
                        String goodsTitle4 = (String) document.get("GoodsTitle");
                        title4.setText(goodsTitle4);
                        String goodsReview4 = (String) document.get("GoodsReview");
                        review4.setText(goodsReview4);
                        String goodsPrice4 = (String) document.get("GoodsPrice");
                        price4.setText(goodsPrice4);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
// 다섯번째 상품
        DocumentReference goods5 = db.collection("StoreGoods").document("w6XoWmhY0t4PBFq8FJ9q");
        goods5.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String storeName5 = (String) document.get("StoreName");
                        store5.setText(storeName5);
                        String goodsTitle5 = (String) document.get("GoodsTitle");
                        title5.setText(goodsTitle5);
                        String goodsReview5 = (String) document.get("GoodsReview");
                        review5.setText(goodsReview5);
                        String goodsPrice5 = (String) document.get("GoodsPrice");
                        price5.setText(goodsPrice5);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }}







