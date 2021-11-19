package com.example.plant01.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.plant01.R;
import com.example.plant01.adaptor.MyAdapter;
import com.example.plant01.adaptor.SliderAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.auth.Users;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Home extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.home_ad1,
            R.drawable.home_ad2,};


    RecyclerView recyclerView;
    ArrayList<Users> usersArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;




//  private FirebaseDatabase database;
//    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
//        setContentView(R.layout.action_bar);

        sliderView = findViewById(R.id.main_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        //
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        usersArrayList = new ArrayList<Users>();
        myAdapter = new MyAdapter(Home.this, usersArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();


        final DrawerLayout drawerLayout1 = (DrawerLayout) findViewById(R.id.drawerLayout);


//        View top = findViewById(R.id.top);

        findViewById(R.id.imagemenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });


    }

    private void EventChangeListener() {

        db.collection("Users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

//                        if (error != null){
//
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Log.e("Firestore error",error.getMessage());
//                            return;
//                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                usersArrayList.add(dc.getDocument().toObject(Users.class));

                            }
                            myAdapter.notifyDataSetChanged();
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
                        }
                    }
                });
    }



}


