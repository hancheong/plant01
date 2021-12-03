package com.example.plant01.garden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class MyMainActivity extends AppCompatActivity {

    private EditText mName , mLocation, mDate;
    private ProgressBar progressBar2;
    private ImageView mProfile;
    private Button mSaveBtn, mShowBtn;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Myplants");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);

        mProfile = (ImageView) findViewById(R.id.iv_PlantsProfile);
        mName = (EditText) findViewById(R.id.edit_name);
        mLocation = (EditText) findViewById(R.id.edit_location);
        mDate = (EditText) findViewById(R.id.edit_date);
        mSaveBtn = (Button) findViewById(R.id.save_btn);
        mShowBtn = (Button) findViewById(R.id.showall_btn);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.INVISIBLE);
        db= FirebaseFirestore.getInstance();

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });




        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyMainActivity.this, ShowActivity.class));
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(MyMainActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
                String name = mName.getText().toString();
                String location = mLocation.getText().toString();
                String date = mDate.getText().toString();
                String id = UUID.randomUUID().toString();
                String profileUri = imageUri.toString();
                saveToFireStore(id, name, location, date, profileUri);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            mProfile.setImageURI(imageUri);

        }
    }

    private void saveToFireStore(String id , String name , String location, String date, String profileUri){

        if (!name.isEmpty() && !location.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("profileUri" ,profileUri );
            map.put("name" , name);
            map.put("location" , location);
            map.put("date" , date);

            db.collection("Myplants").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MyMainActivity.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MyMainActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                }
            });
        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }

    private void uploadToFirebase(Uri uri){

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getfileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Model model = new Model(uri.toString());
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        progressBar2.setVisibility(View.INVISIBLE);

                        Toast.makeText(MyMainActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar2.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar2.setVisibility(View.INVISIBLE);
                Toast.makeText(MyMainActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getfileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}
