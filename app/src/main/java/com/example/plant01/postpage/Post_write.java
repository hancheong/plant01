package com.example.plant01.postpage;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;
import java.util.UUID;

public class Post_write extends AppCompatActivity {
    private EditText Title , Contents;
    private Button Image, Video, Post;
    private ImageButton back;
    private FirebaseFirestore db;
    View.OnClickListener cl;
    private Object Post_write;


    @Override
    public void onBackPressed() {
//        if(Post_write != null){ //상세정보창 프래그먼트를 킨 상태면 뒤로가기했을 때 해당 프래그먼트를 삭제해줌
//            getSupportFragmentManager().beginTransaction().remove((Fragment) Post_write).commit();
//            Post_write = null;
//        }else {
//            super.onBackPressed();
//        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_write);


        Title = (EditText) findViewById(R.id.title);
        Contents = (EditText) findViewById(R.id.contentEditText);
        Image = (Button) findViewById(R.id.img);
        Video = (Button) findViewById(R.id.vod);
        Post = (Button) findViewById(R.id.postbtn);
        back = (ImageButton) findViewById(R.id.post_back);

        db = FirebaseFirestore.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                onBackPressed();
            }
        });

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivity(intent);
            }
        });

        Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setType("video/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent1);
            }
        });

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title.getText().toString();
                String contents = Contents.getText().toString();
                String id = UUID.randomUUID().toString();

                saveToFireStore(id, title, contents);
            }
            private void saveToFireStore(String userid, String title, String contents) {

                if (!title.isEmpty() && !contents.isEmpty()) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("UserId", userid);
                    map.put("Title", title);
                    map.put("Contents", contents);

                    db.collection("WritePosts").document(userid).set(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Post_write.this, "Post Data!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Post_write.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });






//        cl = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.img:
//                        //이미지를 갤러리에서 가져옴
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.setType("image/*");
//                        startActivity(intent);
//                        break;
//                    case R.id.vod:
//                        //동영상을 갤러리에서 가져옴
//                        Intent intent1 = new Intent();
//                        intent1.setType("video/*");
//                        intent1.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivity(intent1);
//                        break;
//                    case R.id.postbtn:
//                        String title = Title.getText().toString();
//                        String contents = Contents.getText().toString();
//                        String id = UUID.randomUUID().toString();
//
//                        saveToFireStore(id, title, contents);
////                        finish();
//                        break;
////                    case R.id.post_back:
////                        finish();
////                        break;
//                }
//
//            }
//
//            private void saveToFireStore(String userid, String title, String contents) {
//
//                if (!title.isEmpty() && !contents.isEmpty()) {
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("UserId", userid);
//                    map.put("Title", title);
//                    map.put("Contents", contents);
//
//                    db.collection("WritePosts").document(userid).set(map)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Post_write.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(Post_write.this, "Failed !!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        };
//        Image.setOnClickListener(cl);
//        Video.setOnClickListener(cl);
//        Post.setOnClickListener(cl);
//        back.setOnClickListener(cl);
    }
}



