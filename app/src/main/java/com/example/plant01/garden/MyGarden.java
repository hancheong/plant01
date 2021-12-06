package com.example.plant01.garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyGarden extends Fragment {

    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PlantsDB> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private RecyclerView main_recyclerView;
    private FirebaseFirestore db;
    private MyAdapter myAdapter;
    private List<Model> list;

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
        FloatingActionButton btnadd2 = (FloatingActionButton)view.findViewById(R.id.btnAdd2);
        btnadd2.setOnClickListener(onClickListener);
//        btnShow = (Button) view.findViewById(R.id.btnShow);

//        btnMove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyMainActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), Context.class));
//            }
//        });

        return view;
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnAdd2:
                    myStartActivity(MyMainActivity.class);
                    break;
            }
        }
    };




    private void myStartActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onStart() {
        super.onStart();

        main_recyclerView = getView().findViewById((R.id.myplant_recycle));
        main_recyclerView.setHasFixedSize(true);
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        myAdapter = new MyAdapter(getActivity(), list);
        main_recyclerView.setAdapter(myAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(myAdapter));
        touchHelper.attachToRecyclerView(main_recyclerView);


        showData();
    }

    private void  showData(){

        db.collection("Myplants").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot : task.getResult()){
                            Model model = new Model(snapshot.getString("id"),snapshot.getString("profileUri"),snapshot.getString("name"),snapshot.getString("location"),snapshot.getString("date"));
                            list.add(model);
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }
}