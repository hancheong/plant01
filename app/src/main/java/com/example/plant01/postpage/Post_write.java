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
import java.util.Stack;

public class Post_write extends BasicActivity {
    EditText title, content, contentEditText;
    Button img, vod, post, imageModify, videoModify, delete;
    View.OnClickListener cl;
    FragmentManager fragmentManager = getFragmentManager();
    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    public static Stack<Fragment> fragmentStack;


    private static final String TAG = "Post_write";
    private FirebaseUser user;
    private ArrayList<String> pathList = new ArrayList<>();
    private  LinearLayout parent;
    private int pathCount, sucessCount;
    private RelativeLayout bottonsBackgroundlayout;
    private ImageView selectedImageVIew;
    private EditText selectedEditText;
    private RelativeLayout loaderLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (requestCode == Activity.RESULT_OK){
                    String profilePath = data.getStringExtra("profilePath");
                    pathList.add(profilePath);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LinearLayout linearLayout = new LinearLayout(Post_write.this);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                    if(selectedEditText == null){
                        parent.addView(linearLayout);
                    }else{
                        for(int i =0; i < parent.getChildCount(); i++) {
                            if (parent.getChildAt(i) == selectedEditText.getParent()) {
                                parent.addView(linearLayout, i + 1);
                                break;
                            }
                        }
                    }
//                    parent.addView(linearLayout);

                    ImageView imageView = new ImageView(Post_write.this);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bottonsBackgroundlayout.setVisibility(View.VISIBLE);
                            selectedImageVIew = (ImageView) view;
                        }
                    });
                    Glide.with(this).load(profilePath).override(1000).into(imageView);
                    linearLayout.addView(imageView);

                    EditText editText = new EditText(Post_write.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    editText.setHint("내용");
                    editText.setOnFocusChangeListener(onFocusChangeListener);
                    linearLayout.addView(editText);
                }
                break;
            case 1:
                if (requestCode == Activity.RESULT_OK){
                    String profilePath = data.getStringExtra("profilePath");
                    Glide.with(this).load(profilePath).override(1000).into(selectedImageVIew);
                }
                break;

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_write);

        parent = findViewById(R.id.contentsLayout);
        bottonsBackgroundlayout = findViewById(R.id.bottonsBackgroundlayout);
        loaderLayout = findViewById(R.id.loaderLyaout);


        bottonsBackgroundlayout = (RelativeLayout)findViewById(R.id.bottonsBackgroundlayout);
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        img = (Button) findViewById(R.id.img);
        vod = (Button) findViewById(R.id.vod);
        post = (Button) findViewById(R.id.postbtn);
        imageModify = (Button) findViewById(R.id.imageModify);
        videoModify = (Button) findViewById(R.id.videoModify);
        delete = (Button) findViewById(R.id.delete);
        contentEditText = (EditText)findViewById(R.id.contentEditText);
        findViewById(R.id.contentEditText).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.title).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    selectedEditText = null;
                }
            }
        });
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img:
//                        //이미지를 갤러리에서 가져옴
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.setType("image/*");
//                        startActivity(intent);
                        mstartActivity(GalleryActivity.class, "image", 0);
                        break;
                    case R.id.vod:
//                        //동영상을 갤러리에서 가져옴
//                        Intent intent1 = new Intent();
//                        intent1.setType("video/*");
//                        intent1.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivity(intent1);
                        mstartActivity(GalleryActivity.class, "video", 0);
                        break;
                    case R.id.postbtn:
                        storageUpload();
//                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                            // 프래그먼트매니저를 통해 사용
//                            PostMainActivity postMainActivity= new PostMainActivity(); // 객체 생성
////                            transaction.replace(R.id.postwrite, postMainActivity); //layout, 교체될 layout
//                            transaction.addToBackStack(null);
//                            transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
                        break;
                    case R.id.bottonsBackgroundlayout:
                        if (bottonsBackgroundlayout.getVisibility() == View.VISIBLE) {
                            bottonsBackgroundlayout.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.imageModify:
                        mstartActivity(GalleryActivity.class, "image", 1);
                        break;
                    case R.id.videoModify:
                        mstartActivity(GalleryActivity.class, "video", 1);
                        break;
                    case R.id.delete:
                        parent.removeView((View) selectedImageVIew.getParent());
                        bottonsBackgroundlayout.setVisibility(View.GONE);
                        break;
                }
            }
        };
        img.setOnClickListener(cl);
        vod.setOnClickListener(cl);
        post.setOnClickListener(cl);
        bottonsBackgroundlayout.setOnClickListener(cl);
        imageModify.setOnClickListener(cl);
        videoModify.setOnClickListener(cl);
        delete.setOnClickListener(cl);
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if(hasFocus){
                selectedEditText = (EditText) view;
            }
        }
    };

    private void storageUpload() {
        final String title = ((EditText) findViewById(R.id.title)).getText().toString();

        if (title.length() > 0) {
            loaderLayout.setVisibility(View.VISIBLE);
            ArrayList<String> contentsList = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = firebaseFirestore.collection("posts").document();

            for(int i = 0; i < parent.getChildCount(); i++){
                LinearLayout linearLayout = (LinearLayout) parent.getChildAt(i);
                for(int ii = 0; ii<linearLayout.getChildCount(); ii++) {
                    View view = linearLayout.getChildAt(ii);
                    if (view instanceof EditText) {
                        String text = ((EditText) view).getText().toString();
                        if (text.length() > 0) {
                            contentsList.add(text);
                        }
                    } else {
                        contentsList.add(pathList.get(pathCount));
                        String[] pathArray = pathList.get(pathCount).split("\\.");
                        final StorageReference mountainImagesRef = storageReference.child("posts/" + documentReference.getId() + "/" + pathCount +pathArray[pathArray.length-1]);
                        try {
                            InputStream stream = new FileInputStream(new File(pathList.get(pathCount)));
                            StorageMetadata metadata = new StorageMetadata.Builder().setCustomMetadata("index", "" + (contentsList.size() - 1)).build();
                            UploadTask uploadTask = mountainImagesRef.putStream(stream, metadata);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    final int index = Integer.parseInt(taskSnapshot.getMetadata().getCustomMetadata("index"));
                                    mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            contentsList.set(index, uri.toString());
                                            sucessCount++;
                                            if (pathList.size() == sucessCount) {
                                                //완료
                                                Writeinfo writeinfo = new Writeinfo(title, contentsList, user.getUid(), new Date());
                                                storeUpload(documentReference, writeinfo);
                                                for (int a = 0; a < contentsList.size(); a++) {
                                                    Log.e("로그: ", "콘텐츠: " + contentsList.get(a));
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                        } catch (FileNotFoundException e) {
                            Log.e("로그", "에러: " + e.toString());
                        }
                        pathCount++;
                    }
                }
            }
            if(pathList.size() == 0){
                Writeinfo writeinfo = new Writeinfo(title, contentsList, user.getUid(), new Date());
                storeUpload(documentReference, writeinfo);
            }

        } else {
            startToast("제목을 입력해주세요.");
        }
    }

    private void storeUpload(DocumentReference documentReference, Writeinfo writeinfo) {
        documentReference.set(writeinfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        Log.d(TAG, "DocumnetSnapshot sucessfully");
                        loaderLayout.setVisibility(View.GONE);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error", e);
                        loaderLayout.setVisibility(View.GONE);
                    }
                });
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void mstartActivity(Class c, String media, int requestCode) {
        Intent intent = new Intent(this, c);
        intent.putExtra("media", media);
        startActivityForResult(intent, requestCode);
    }

}




