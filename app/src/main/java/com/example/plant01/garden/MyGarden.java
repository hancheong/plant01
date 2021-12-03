package com.example.plant01.garden;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.plant01.R;
import com.google.android.exoplayer2.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyGarden extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PlantsDB> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    TextView tvGarden;
    ImageButton ibnBack;
    Button btnAdd, btnShow;
    ListView listView;
    //CustomAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        View view = inflater.inflate(R.layout.garden_my_garden, container, false);
        view.findViewById(R.id.btnAdd).setOnClickListener(onClickListener);

//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<>(); // user 객체를 담을 어레이 리스트 (to adaptor)
//        database = FirebaseDatabase.getInstance();

//        databaseReference = database.getReference("Myplants"); // db테이블 연결
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {


        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnShow = (Button) view.findViewById(R.id.btnShow);

//        btnMove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyMainActivity.class);
//                startActivity(intent);
//            }
//        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShowActivity.class));
            }
        });

        return view;
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnAdd:
                    myStartActivity(MyMainActivity.class);
                    break;
            }
        }
    };



    private void myStartActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivityForResult(intent, 0);
    }


}