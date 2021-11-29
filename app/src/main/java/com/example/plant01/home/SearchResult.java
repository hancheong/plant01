package com.example.plant01.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SearchResult extends AppCompatActivity {
    private FirebaseFirestore db;
    ImageView plantImg;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        Intent intent = getIntent(); /*데이터 수신*/

        String getplantname = intent.getExtras().getString("plantName");
        Log.e("식물이름", getplantname);
        plantImg = (ImageView)findViewById(R.id.search_result_img);

        db = FirebaseFirestore.getInstance();

        Query plantinfo = db.collection("Plant").whereEqualTo("plantName", getplantname);

        plantinfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String plantImgurl = (String) document.get("plantImg");
                        Log.e("식물테이블", plantImgurl);
                        Glide.with(SearchResult.this)
                                .load(Uri.parse(plantImgurl))
                                .into(plantImg);
                    }
                }
            }
        });
    }
}