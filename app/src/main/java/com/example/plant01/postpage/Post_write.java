package com.example.plant01.postpage;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.plant01.R;

import java.util.Stack;

public class Post_write extends AppCompatActivity {
    EditText title, content;
    Button img, vod, post;
    View.OnClickListener cl;
    Intent intent1;
    FragmentManager fragmentManager = getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    public static Stack<Fragment> fragmentStack;



//    private static final String TAG = "WritePost";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_write);

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        img = (Button) findViewById(R.id.img);
        vod = (Button) findViewById(R.id.vod);
        post = (Button) findViewById(R.id.postbtn);

        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.postbtn, Post_diary.newInstance());

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //액티비티에서 프래그먼트 이동 아직 미완,,
                androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
                androidx.fragment.app.FragmentManager supportFragmentManager = null;
                supportFragmentManager.beginTransaction()
                        .replace(R.id.postbtn, Post_diary.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
//                        .setMaxLifecycle()
                        .commit();
//
//                Post_diary fragment =
//                        (Post_diary) fragmentManager.findFragmentById(R.id.postbtn);
            }
        });
//        intent1 = getIntent();

//        cl = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.img:
//                        break;
//                    case R.id.vod:
//                        break;
//                    case R.id.postbtn:
//
//                        break;
//                }
//            }
//        };
//        img.setOnClickListener(cl);
//        vod.setOnClickListener(cl);
//        post.setOnClickListener(cl);

    }
}
