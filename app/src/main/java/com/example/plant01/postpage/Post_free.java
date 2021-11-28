package com.example.plant01.postpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plant01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Post_free extends Fragment {
    private static final String TAG = "Post_free";
    private View view;
    private FloatingActionButton floatingActionButton;
    private View.OnClickListener cl;
//리사이클러뷰 > 파이어베이스
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore database;

    public static Post_free newInstance(){
        Post_free post_free = new Post_free();
        return post_free;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.post_free, container, false);

//        recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(false); //리사이클러뷰 기존 성능 강화
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
////        layoutManager = new LinearLayoutManager(getActivity());
////        recyclerView.setLayoutManager(layoutManager);



        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_post);
        //작성페이지로 이동하는 버튼
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Post_write.class);
                startActivity(intent);
            }
        };
        floatingActionButton.setOnClickListener(cl);
        return view;

//        recyclerView = getView().findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존 성능 강화
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<Post>(); // Post객체를 담을 어레이 리스트 (어뎁터쪽)
//
//        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
//        databaseReference = database.getReference("Post"); //DB 테이블 연결
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                arrayList.clear(); //기존 배열 초기화
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Post post = snapshot.getValue(Post.class);
//                    arrayList.add(post);
//                }
//                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("Post", String.valueOf(error.toException())); //에러문 출력
//            }
//        });
//        adapter = new post_CustomAdapter(arrayList, getContext());
//        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        //작성버튼


    }


    @Override
    public void onStart() {
        super.onStart();

//        postItemArrayList = new ArrayList<PostItem>(); // Post객체를 담을 어레이 리스트 (어뎁터쪽)
//
////        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
//       database = FirebaseFirestore.getInstance();
//       showPost();
//        databaseReference = database.getReference("Post"); //DB 테이블 연결
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                arrayList.clear(); //기존 배열 초기화
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Post post = snapshot.getValue(Post.class);
//                    arrayList.add(post);
//                }
//                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("Post", String.valueOf(error.toException())); //에러문 출력
//            }
//        });
//        adapter = new post_CustomAdapter(arrayList, getContext());
//        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
//        return;
    }

    private void showPost() {
//        database.collection("post")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value,
//                                        @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.e(TAG, "Listen failed.", e);
//                            return;
//                        }
//
//                        List<String> cities = new ArrayList<>();
//                        for (QueryDocumentSnapshot doc : value) {
//                            if (doc.get("contentImg") != null) {
//                                cities.add(doc.getString("contentImg"));
//                            }
//                        }
//                        Log.d(TAG, "contentImg: " + cities);
//                    }
//                });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //시도중
//    @Override
//    public void onStart() {
//        super.onStart();
//        recyclerView = getView().findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존 성능 강화
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<Post>(); // Post객체를 담을 어레이 리스트 (어뎁터쪽)
//
//        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
//        databaseReference = database.getReference("Post"); //DB 테이블 연결
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                arrayList.clear(); //기존 배열 초기화
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Post post = snapshot.getValue(Post.class);
//                    arrayList.add(post);
//                }
//                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("Post", String.valueOf(error.toException())); //에러문 출력
//            }
//        });
//        adapter = new post_CustomAdapter(arrayList, getContext());
//        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
//        return;
//    }
}
