package com.example.plant01.garden;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plant01.R;

public class AddPlants extends AppCompatActivity {

    EditText etName, etLocation, etDate;
    TextView tvTitle;
    Button btnBack, btnSave;
    ImageView ivPlants;
    View.OnClickListener cl;

    private static  final int PLANTS_IMAGE_CODE = 1000;
    private static  final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        etName = (EditText) findViewById(R.id.etName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etDate = (EditText) findViewById(R.id.etDate);

        ivPlants = (ImageView) findViewById(R.id.ivPlants);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (Button) findViewById(R.id.btnBack);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyGarden.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPlants.class);
                startActivity(intent);
            }
        });

        ivPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                        String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }

                    else {PickImageFromGallery();}
                }
            }
        });

    }

    private void PickImageFromGallery() {
        // Intent for Pick Image From Gallery

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PLANTS_IMAGE_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == PLANTS_IMAGE_CODE) {
            //set Image into ivPlants (ImageView)
            ivPlants.setImageURI(data.getData());
        }
    }
}
