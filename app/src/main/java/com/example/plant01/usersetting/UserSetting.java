package com.example.plant01.usersetting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class UserSetting extends AppCompatActivity {

    private ImageView profileImageVIew, btnCamera, btnGallery;
    private RelativeLayout loaderLayout;
    private RelativeLayout buttonBackgroundLayout;
    private String profilePath;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    Toolbar toolbar;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_setting_user);
        toolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        setTitle("회원정보");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        profileImageVIew = (ImageView) findViewById(R.id.img_setting_user);

        btnGallery = (ImageView) findViewById(R.id.btn_gallery);

        profileImageVIew.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_setting_user:
                    showcameraDialog();
                    break;
                case R.id.btn_camera:
                    opencamera();
                    break;

            }
        }
    };

    /*-----------프로필사진 누르면 뜨는 다이어로그 -----------------*/
    public void showcameraDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserSetting.this, R.style.AlterDialogTheme);
        View view = LayoutInflater.from(UserSetting.this).inflate(R.layout.home_dialog_camera
                , (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        AlertDialog alterDialog = builder.create();
        if (alterDialog.getWindow() != null) {
            alterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        btnCamera = (ImageView) view.findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(onClickListener);
        alterDialog.show();

    }

    /*----------카메라 열기-----------------*/
    public void opencamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }


    /*-------------카메라 사진 보여주기---------------------*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            profilePath = data.getStringExtra("profilePath");
            FirebaseStorage storage = FirebaseStorage.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            db = FirebaseFirestore.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference mountainImagesRef = storageRef.child("userprofile/"+user.getUid()+"/profile.jpg");


            Bitmap bitmap = (Bitmap) data.getParcelableExtra("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();


            UploadTask uploadTask = mountainImagesRef.putBytes(data1);
            mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.e("이미지주소", uri.toString());
                    db.collection("Users").document(user.getUid())
                            .update("userImg", uri.toString());
                }
            });
            profileImageVIew.setImageBitmap(bitmap);

        }
    }

}