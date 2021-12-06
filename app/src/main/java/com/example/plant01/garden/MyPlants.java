package com.example.plant01.garden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyPlants extends AppCompatActivity {

    TextView tvName, tvLocation, tvDate;
    ImageView ivPlants;
    Button btnBack2;
    String name, location, date, profile;
    String myplantid;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_my_plants);

        btnBack2 = findViewById(R.id.btnBack2);
        tvName = (TextView) findViewById(R.id.tvName);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDate = (TextView) findViewById(R.id.tvDate);
        ivPlants = (ImageView) findViewById(R.id.ivPlants);

        Intent intent = getIntent();
        myplantid = intent.getStringExtra("myplantid");
        Log.e("String myplant", myplantid);
        getmyplantinfo();
//        tvName.setText(intent.getStringExtra("myplantid"));
//        tvLocation.setText(intent.getStringExtra("location"));
//        tvDate.setText(intent.getStringExtra("date"));
//        ivPlants.setImageURI(Uri.parse(intent.getStringExtra("profile")));

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.myPlant, new MyGarden()).commit();
                btnBack2.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void getmyplantinfo(){
        db = FirebaseFirestore.getInstance();
        db.collection("Myplants").document(myplantid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                Log.e("taskgetResult", task.getResult().toString());
                tvName.setText(doc.get("name").toString());
                tvLocation.setText(doc.get("location").toString());
                tvDate.setText(doc.get("date").toString());
                if(doc.get("profileUri").toString() != null){
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(doc.get("profileUri").toString()))
                            .into(ivPlants);
//                    ivPlants.setImageURI(Uri.parse(doc.get("profileUri").toString()));
                }


            }
        });
    }



}