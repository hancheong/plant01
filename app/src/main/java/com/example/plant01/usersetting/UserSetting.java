package com.example.plant01.usersetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.plant01.R;

public class UserSetting extends AppCompatActivity {

    private ImageView profileImageVIew;
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

        profileImageVIew = (ImageView) findViewById(R.id.btn_profile);
        ImageView btnCamera = (ImageView) findViewById(R.id.btn_camera);
        ImageView btnGallery = (ImageView) findViewById(R.id.btn_gallery);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_profile:
                    showDialog();
                    break;
            }
        }
    };

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UserSetting.this);
        View view = LayoutInflater.from(UserSetting.this).inflate(R.layout.home_dialog_camera
                , (ConstraintLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);

    }


}