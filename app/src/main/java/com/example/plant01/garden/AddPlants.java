package com.example.plant01.garden;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.plant01.R;

public class AddPlants extends AppCompatActivity {

    EditText etName, etLocation, etDate;
    TextView tvTitle;
    Button btnBack, btnSave;
    ImageView ivPlants;
    Intent intent;

    private static  final int PLANTS_IMAGE_CODE = 1000;
    private static  final int PERMISSION_CODE = 1001;
    private static  final int REQUEST_CODE = 1002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        etName = (EditText) findViewById(R.id.etName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etDate = (EditText) findViewById(R.id.etDate);

        ivPlants = (ImageView) findViewById(R.id.ivPlants);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        intent = new Intent(this,MyPlants.class);

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
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("location", etLocation.getText().toString());
                intent.putExtra("date", etDate.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);

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
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null);{
            String result = data.getStringExtra("result");
            Toast.makeText(AddPlants.this,result,Toast.LENGTH_SHORT);
        }
    }

}
