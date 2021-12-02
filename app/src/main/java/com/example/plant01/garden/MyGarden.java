package com.example.plant01.garden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import android.content.res.TypedArray;
import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;
import com.google.android.exoplayer2.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

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
    Button btnAdd, btnMove;
    ListView listView;
    //CustomAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        View view = inflater.inflate(R.layout.garden_my_garden, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // user 객체를 담을 어레이 리스트 (to adaptor)
        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Myplants"); // db테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //파이어베이스 데이터베이스의 데이터 받아오는 곳
                arrayList.clear(); // 기존 배열 초기화
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    PlantsDB plantsDB = snapshot1.getValue(PlantsDB.class);//만든 객체에 데이터 담기
                    arrayList.add(plantsDB);// 담은 데이터들을 배열 리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();// 리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러 발생시
                Log.e("MyGarden", String.valueOf(error.toException())); //에러문 출력
            }
        });
        adapter = new CustomAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter);//리사이클러뷰에 adaptor 연결


        tvGarden = (TextView) view.findViewById(R.id.tvGarden);
        ibnBack = (ImageButton) view.findViewById(R.id.ibnBack);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnMove = (Button) view.findViewById(R.id.btnMove);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyMainActivity.class);
                startActivity(intent);
            }
        });

        ibnBack.setOnClickListener(new View.OnClickListener() {//뒤로가기 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HomeFragment.class);
                startActivity(intent);
            }
        });

        return view;
    }

}