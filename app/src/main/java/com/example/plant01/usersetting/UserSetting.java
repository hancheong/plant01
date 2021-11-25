package com.example.plant01.usersetting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

public class UserSetting extends AppCompatActivity {

    private ImageView profileImageVIew, btnCamera, btnGallery;
    private RelativeLayout loaderLayout;
    private RelativeLayout buttonBackgroundLayout;
    private String profilePath;
    Toolbar toolbar;

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

            FirebaseStorage storage = FirebaseStorage.getInstance();

            // [START upload_create_reference]
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

//            // Create a reference to "mountains.jpg"
//            StorageReference mountainsRef = storageRef.child("mountains.png");
//
//            // Create a reference to 'images/mountains.jpg'
            StorageReference mountainImagesRef = storageRef.child("images/mountains.png");

            //

            Bitmap bitmap = (Bitmap) data.getParcelableExtra("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();

            UploadTask uploadTask = mountainImagesRef.putBytes(data1);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                }
            });
            profileImageVIew.setImageBitmap(bitmap);


        }
    }


    private HandlerThread mBackgroundThread;

    /**
     * A {@link Handler} for running tasks in the background.
     */
    private Handler mBackgroundHandler;

    /**
     * An {@link ImageReader} that handles still image capture.
     */
    private ImageReader mImageReader;

    /**
     * This is the output file for our picture.
     */
    private File mFile;

    /**
     * This a callback object for the {@link ImageReader}. "onImageAvailable" will be called when a
     * still image is ready to be saved.
     */
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener
            = new ImageReader.OnImageAvailableListener() {

        @Override
        public void onImageAvailable(ImageReader reader) {
            mBackgroundHandler.post(new UserSetting.ImageUpLoader(reader.acquireNextImage()));
        }

    };


    private static class ImageUpLoader implements Runnable {

        /**
         * The JPEG image
         */
        private final Image mImage;

        /**
         * The file we save the image into.
         */

        ImageUpLoader(Image image) {
            mImage = image;
        }

        @Override
        public void run() {
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

            UploadTask uploadTask = mountainImagesRef.putBytes(bytes);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("실패", "실패");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Log.e("성공", "성공");
                }
            });


        }


    }
}