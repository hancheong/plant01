package com.example.plant01.store;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;


public class store extends Fragment {
    private View view;
    private TextView more;
    private FirebaseFirestore db;
    private ImageButton store_resultback;
    private RecyclerView recyclerView;
    private store_GoodsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private store_GoodsAdapter store_goodsAdapter;
    private List<StoreGoods> list;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView nothing;

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
        return view;
    }
}

