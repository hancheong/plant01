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

public class Post_best extends Fragment {
    private View view;
    private FloatingActionButton floatingActionButton;
    private View.OnClickListener cl;

    public Post_best(){}
    public static Post_best newInstance(){
        Post_best post_best = new Post_best();
        return post_best;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.post_best, container, true);
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
    }



    @Override
    public void onStop() {
        super.onStop();
    }
}
