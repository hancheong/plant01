package com.example.plant01.store;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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


public class store extends Fragment {
    private View view;
    private TextView more;
    private FirebaseDatabase db;

    TextView store1, title1, review1, price1;
    TextView store2, title2, review2, price2;
    TextView store3, title3, review3, price3;

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



    @Override
    public void onStart() {
        super.onStart();
//        showData();
    }



//    private void showData() {
//        store1 = view.findViewById(R.id.RecommendStore1);
//        title1 = view.findViewById(R.id.RecommendStore1Title);
//        review1 = view.findViewById(R.id.Review1);
//        price1 = view.findViewById(R.id.Price1);
//
//        store2 = view.findViewById(R.id.RecommendStore2);
//        title2 = view.findViewById(R.id.RecommendStore2Title);
//        review2 = view.findViewById(R.id.Review2);
//        price2 = view.findViewById(R.id.Price2);
//
//        store3 = view.findViewById(R.id.RecommendStore3);
//        title3 = view.findViewById(R.id.RecommendStore3Title);
//        review3 = view.findViewById(R.id.Review3);
//        price3 = view.findViewById(R.id.Price3);
//
//
//    // 첫번째 상품
//    DocumentReference goods1 = db.collection("StoreGoods").document("8hIJAMJMC9WA0sw7hKp8");
//        goods1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()) {
//                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    String storeName1 = (String) document.get("StoreName");  // 스토어 이름 지정
//                    store1.setText(storeName1);
//                    String goodsTitle1 = (String) document.get("GoodsTitle"); // 상품 제목 지정
//                    title1.setText(goodsTitle1);
//                    String goodsReview1 = (String) document.get("GoodsReview"); // 상품 리뷰 지정
//                    review1.setText(goodsReview1);
//                    String goodsPrice1 = (String) document.get("GoodsPrice"); // 상품 가격 지정
//                    price1.setText(goodsPrice1);
//                } else {
//                    Log.d(TAG, "No such document"); // 문서를 못찾았을 경우
//                }
//            } else {
//                Log.d(TAG, "get failed with ", task.getException()); // 실패했을 경우. 이하 상품 코드 동일
//            }
//        }
//    });
//    // 두번째 상품
//    DocumentReference goods2 = db.collection("StoreGoods").document("brAE7c0BTuhQqInLY73s");
//        goods2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()) {
//                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    String storeName2 = (String) document.get("StoreName");
//                    store2.setText(storeName2);
//                    String goodsTitle2 = (String) document.get("GoodsTitle");
//                    title2.setText(goodsTitle2);
//                    String goodsReview2 = (String) document.get("GoodsReview");
//                    review2.setText(goodsReview2);
//                    String goodsPrice2 = (String) document.get("GoodsPrice");
//                    price2.setText(goodsPrice2);
//                } else {
//                    Log.d(TAG, "No such document");
//                }
//            } else {
//                Log.d(TAG, "get failed with ", task.getException());
//            }
//        }
//    });
//    // 세번째 상품
//    DocumentReference goods3 = db.collection("StoreGoods").document("ki4XlFCvbFUHyh92v0oT");
//        goods3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()) {
//                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    String storeName3 = (String) document.get("StoreName");
//                    store3.setText(storeName3);
//                    String goodsTitle3 = (String) document.get("GoodsTitle");
//                    title3.setText(goodsTitle3);
//                    String goodsReview3 = (String) document.get("GoodsReview");
//                    review3.setText(goodsReview3);
//                    String goodsPrice3 = (String) document.get("GoodsPrice");
//                    price3.setText(goodsPrice3);
//                } else {
//                    Log.d(TAG, "No such document");
//                }
//            } else {
//                Log.d(TAG, "get failed with ", task.getException());
//            }
//        }
//    });

}
