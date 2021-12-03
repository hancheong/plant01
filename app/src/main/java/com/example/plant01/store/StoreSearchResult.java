package com.example.plant01.store;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StoreSearchResult extends AppCompatActivity {

    private ImageButton store_resultback;
    private ImageButton store_back;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db;
    private store_GoodsAdapter store_goodsAdapter;
    private List<StoreGoods> list;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_searchresult);

//        view = inflater.inflate(R.layout.frag_like, container, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<>();

        store_resultback = (ImageButton) findViewById(R.id.store_resultback);
        store_resultback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        TextView result = (TextView) findViewById(R.id.store_resulttext);
        String name = intent.getStringExtra("contact_search");
        if (name != null)
            result.setText(name);

//        showData();

    }

    Query search = db.collection("StoreGoods").whereEqualTo("goodsKind","선인장");



//    private void showData() {
//
//        Intent intent = getIntent();
//
//        db.collection("StoreGoods")
//                .whereEqualTo("goodsKind", intent.getStringExtra("contact_search"))
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//
//
//    }
}

