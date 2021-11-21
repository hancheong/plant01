package com.example.plant01.postpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plant01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Post_diary extends Fragment {
    private View view;
    private FloatingActionButton floatingActionButton;
    private View.OnClickListener cl;
//    private RecyclerView recyclerView;
//    private ArrayList<Post> list = new ArrayList<>();
//    private Post_RecyclerAdapter post_recyclerAdapter;

    public static Post_diary newInstance(){
        Post_diary post_diary = new Post_diary();
        return post_diary;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.post_diary11, container, false);
//        recyclerView.setHasFixedSize(true);
//        post_recyclerAdapter = new Post_RecyclerAdapter(getActivity(), list);
        view = inflater.inflate(R.layout.post_diary, container, false);

//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentActivity activitiy = null;
//                FragmentManager manager = activitiy.getSupportFragmentManager();
//                int stackCount = manager.getBackStackEntryCount();
//
//                // 내가 찾고자 하는 fragment가 있을 때 까지 끈다.
//                for ( int i = stackCount -1; i >=0; i-- ) {
//                    if ( !manager.getBackStackEntryAt(i).getName().equals("post")) {
//                        manager.popBackStack();
//                    }
//                    else {
//                        break;
//                    }
//                }
//        }
// });

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
//        return null;
    }

}
