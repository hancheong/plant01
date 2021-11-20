package com.example.plant01.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plant01.R;
import com.example.plant01.adaptor.SliderAdapter;
import com.example.plant01.adaptor.home_PlantAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    SliderView sliderView;
    int[] images = {R.drawable.home_ad1,
            R.drawable.home_ad2,};


    RecyclerView recyclerView;
    ArrayList<Plants> plantsArrayList;
    home_PlantAdapter plantAdapter;
    FirebaseDatabase database;
    DatabaseReference databaserf;
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_activity, container, false);

    }


    //  private FirebaseDatabase database;
//    private DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();

        sliderView = getView().findViewById(R.id.main_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        //
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        //드로워
        final DrawerLayout drawerLayout1 = (DrawerLayout) getView().findViewById(R.id.drawerLayout);


        getView().findViewById(R.id.imagemenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });




        plantsArrayList = new ArrayList<Plants>();
        plantAdapter = new home_PlantAdapter(getContext(), plantsArrayList);

        recyclerView.setAdapter(plantAdapter);
        db = FirebaseFirestore.getInstance();
//        database = FirebaseDatabase.getInstance();// firebaseDatabase에서 객체가져옴
        showRecomendPlant();

//        recyclerView.setAdapter(plantAdapter); // 리사이클러뷰에 어댑터 연결
    }


    public  void  showRecomendPlant() {
        db.collection("Plants").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                plantsArrayList.clear();
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        plantsArrayList.add(dc.getDocument().toObject(Plants.class));
                    }

                    plantAdapter.notifyDataSetChanged();

                }
            }
        });
    }


}

//    private void EventChangeListener() {
//        db.collection("Plants").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                plantsArrayList.clear();
//                for (DocumentChange dc : value.getDocumentChanges()) {
//                    if (dc.getType() == DocumentChange.Type.ADDED) {
//                        plantsArrayList.add(dc.getDocument().toObject(Plants.class));
//
//                    }
//                    plantAdapter.notifyDataSetChanged();
//
//                }
//            }
//        });
//    }





//    public void getPlant()
//    {
//        databaserf.addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                plantsArrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
//                    Plants plant = snapshot.getValue(Plants.class); // 만들어뒀던 Users 객체에 데이터를 담는다.
//                    plantsArrayList.add(plant); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
//                }
//                plantAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // 디비를 가져오던중 에러 발생 시
//                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
//            }
//        });
//    }







