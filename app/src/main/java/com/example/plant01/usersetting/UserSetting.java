package com.example.plant01.usersetting;

//import static com.example.plant01.usersetting.Util.INTENT_PATH;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 0: {
//                if (resultCode == Activity.RESULT_OK) {
//                    profilePath = data.getStringExtra(INTENT_PATH);
////                    Glide.with(this).load(profilePath).centerCrop().override(500).into();
////                    buttonBackgroundLayout.setVisibility(View.GONE);
//                }
//                break;
//            }
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}