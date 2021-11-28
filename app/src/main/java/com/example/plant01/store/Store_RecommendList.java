package com.example.plant01.store;

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
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
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
import java.util.Map;

public class Store_RecommendList extends AppCompatActivity {
    private static final String TAG = "Store_RecommendList";

    private ImageButton store_back;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaserf;
    ArrayList<StoreGoods> storeGoodsArrayList;
    store_GoodsAdapter store_GoodsAdapter;
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
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        storeGoodsArrayList = new ArrayList<StoreGoods>();
        store_GoodsAdapter = new store_GoodsAdapter(storeGoodsArrayList, getApplicationContext());
        db = FirebaseFirestore.getInstance();
        showRecommendGoods();

    }


    public void showRecommendGoods() {
        db.collection("StoreGoods").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                storeGoodsArrayList.clear();
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        storeGoodsArrayList.add(dc.getDocument().toObject(StoreGoods.class));
                    }

                    store_GoodsAdapter.notifyDataSetChanged();

                }
            }
        });

//    private String uid = 8hIJAMJMC9WA0sw7hKp8;
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collections("StoreGoods").document(uid).get().addOnCompleteListener(task -> {
//        if (task.getException() != null) {
//            return;
//        }
//
//        DocumentSnapshot snapshot = task.getResult();
//        String StoreName = snapshot.getString("StoreName");
//        textview.setText(StoreName);
//    });

    }

    public void showGoods() {

        store1 = (TextView) findViewById(R.id.recommend_store1);
        store2 = (TextView) findViewById(R.id.recommend_store2);
        store3 = (TextView) findViewById(R.id.recommend_store3);
        store4 = (TextView) findViewById(R.id.recommend_store4);
        store5 = (TextView) findViewById(R.id.recommend_store5);

        title1 = (TextView) findViewById(R.id.recommend_title1);
        title2 = (TextView) findViewById(R.id.recommend_title2);
        title3 = (TextView) findViewById(R.id.recommend_title3);
        title4 = (TextView) findViewById(R.id.recommend_title4);
        title5 = (TextView) findViewById(R.id.recommend_title5);

        review1 = (TextView) findViewById(R.id.recommend_review1);
        review2 = (TextView) findViewById(R.id.recommend_review2);
        review3 = (TextView) findViewById(R.id.recommend_review3);
        review4 = (TextView) findViewById(R.id.recommend_review4);
        review5 = (TextView) findViewById(R.id.recommend_review5);

        price1 = (TextView) findViewById(R.id.recommend_price1);
        price2 = (TextView) findViewById(R.id.recommend_price2);
        price3 = (TextView) findViewById(R.id.recommend_price3);
        price4 = (TextView) findViewById(R.id.recommend_price4);
        price5 = (TextView) findViewById(R.id.recommend_price5);

        Query freegoods = db.collection("StoreGoods").whereEqualTo("StoreName","No.1화분마켓").limit(1);

        freegoods.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        ArrayList list = (ArrayList)document.getData().get("likes");

                        //상품 제목 가져오기
                        String GoodsTitle = (String)document.get("GoodsTitle");
                        title1.setText(GoodsTitle);

                        //상품 리뷰 수 가져오기
                        String GoodsReview = (String)document.get("GoodsReview");
                        review1.setText(GoodsReview);

                        //상품 가격 가져오기
                        String GoodsPrice = (String)document.get("GoodsPrice");
                        price1.setText(GoodsPrice);

//                        //GoodsUID로 StoreName 가져오기
//                        String GoodsUID = (String) document.get("GoodsUID");
//                        db.collection("StoreGoods").document(GoodsUID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DocumentSnapshot doc = task.getResult();
//
//                                    // 스토어 이름과 이미지
//                                    String StoreName = (String) doc.get("StoreName");
//                                    store1.setText(StoreName);
//                                    String userImg = (String) doc.get("userImg");
//                                    if(userImg != null){
//                                        Glide.with(getContext())
//                                                .load(Uri.parse(userImg))
//                                                .into(freeuserprofile);
//                                    }
//
//                                    Log.d(TAG, doc.getId() + " " + UserUID + userNick);
//
//
//
//                                }
//                            }
//                        });
//
//
//                        Log.d(TAG, document.getId() + " => " + document.getData()+ " " + UserUID + " "  );
//                    }
//                } else {
//                    Log.d(TAG, "Error getting documents: ", task.getException());
//                }
//
//            }
//        });




    }

}}});}}

