package com.example.plant01.home;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.example.plant01.adaptor.SliderAdapter;
import com.example.plant01.adaptor.home_PlantAdapter;
import com.example.plant01.usersetting.UserSetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    SliderView sliderView;
    int[] images = {R.drawable.home_ad1,
            R.drawable.home_ad2,};


    RecyclerView recyclerView;
    ArrayList<Plants> plantsArrayList;
    home_PlantAdapter plantAdapter;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaserf;
    FirebaseFirestore db;
    View.OnClickListener cl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_main_fragment, container, false);

    }


    //  private FirebaseDatabase database;
//    private DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        sliderView = getView().findViewById(R.id.main_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();




        /*----------------추천상품 부분 -------------------------------*/

        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        plantsArrayList = new ArrayList<Plants>();
        plantAdapter = new home_PlantAdapter(getContext(), plantsArrayList);
        recyclerView.setAdapter(plantAdapter);
        db = FirebaseFirestore.getInstance();
        showRecomendPlant();



        /*------------------드로워부분-------------------*/

        final DrawerLayout drawerLayout1 = (DrawerLayout) getView().findViewById(R.id.drawerLayout);
        NavigationView navigationView = getView().findViewById(R.id.nv_homedrawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_profile:
                        Intent intent1 = new Intent(getActivity(), UserSetting.class);
                        startActivity(intent1);
                        break;
                    case R.id.btn_bell:
                        Intent intent2 = new Intent(getActivity(), bell.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

        ///
        showBoard();


        /*--------- 클릭 이벤트 -------------*/
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imagemenu: //햄버거 버튼
                        showUserProfile();
                        drawerLayout1.openDrawer(GravityCompat.START);
                }

            }
        };
        getView().findViewById(R.id.imagemenu).setOnClickListener(cl);

    }


    /*------------------추천상품 보여주는 부분----------------------------------*/
    public void showRecomendPlant() {
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


    /*-----------------드로워 유저 프로필 보여주는 부분분---------------------------*/
    public void showUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //부분업데이트?어떻게
        CircleImageView userprofile = getView().findViewById(R.id.iv_userProfile);
        TextView usernick = getView().findViewById(R.id.tv_userNick);
        db.collection("Users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    //해당 필드의 값 받아오기
                    String userImg = (String) doc.get("userImg");
                    String userNick = (String) doc.get("userNick");
                    usernick.setText(userNick);
                    Glide.with(getContext())
                            .load(Uri.parse(userImg))
                            .into(userprofile);
                }
            }

        });
    }


    /*-------------인기게시판 불러오는 곳------------------*/

    public void showBoard(){
        RoundedImageView freeuserprofile = getView().findViewById(R.id.free_userpfile);

        TextView free_content = getView().findViewById(R.id.freeDetail);
        TextView free_count = getView().findViewById(R.id.free_likecount);

        //like필드를 ??오름차순???

        /*----------------자유게시판 불러오는 쿼리 -------------------------*/

        Query freeboard = db .collection("Posts").whereEqualTo("board","1");
        Query query = db.collection("Posts").orderBy("likes", Query.Direction.ASCENDING).limit(1);

        /*-------------------질문 게시판 불러오는 쿼리 ---------------------------*/
        Query questboard = db .collection("Posts").whereEqualTo("board","2");
        Query query1 = freeboard.orderBy("likes", Query.Direction.ASCENDING).limit(1);
        //postUserUID 가져오는 방법?
        //postLike의 documentID가져옴.
       query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()) {
                   for (QueryDocumentSnapshot document : task.getResult()) {
                       ArrayList list = (ArrayList)document.getData().get("likes");


                       //int를 String으로 바꾸는 방법
                       String  likes = String.valueOf(list.size());
                       free_count.setText(likes);
                       
                       //컨텐츠 내용 가져오기

                       //postUserID로 userNick가져오기
                       String UserUID = (String) document.get("postUserUID");
                       TextView free_usernick = getView().findViewById(R.id.free_usernic);
                       db.collection("Users").document(UserUID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                           @Override
                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                               if (task.isSuccessful()) {
                                   DocumentSnapshot doc = task.getResult();
                                   String userNick = (String) doc.get("userNick");
                                   free_usernick.setText(userNick);
                                   Log.d(TAG, doc.getId() + " " + UserUID + userNick);


                               }
                           }
                       });


                       Log.d(TAG, document.getId() + " => " + document.getData()+ " " + UserUID + " "  );
                   }
               } else {
                   Log.d(TAG, "Error getting documents: ", task.getException());
               }

           }
       });
    }
}








