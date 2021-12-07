package com.example.plant01.garden;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private String uName, uLocation, uDate , uId, uProfileUri, listsize;
    String myplantid = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        StorageReference list = reference.child("Myplants/"+user.getUid());
        list.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> a = listResult.getPrefixes();
                listsize = String.valueOf(a.size()+1);
                Log.e("listsize", listsize);
            }
        });

        mProfile = (ImageView) findViewById(R.id.iv_PlantsProfile);
        mName = (EditText) findViewById(R.id.edit_name);
        mLocation = (EditText) findViewById(R.id.edit_location);
        mDate = (EditText) findViewById(R.id.edit_date);
        mSaveBtn = (Button) findViewById(R.id.save_btn);
        mShowBtn = (Button) findViewById(R.id.showall_btn);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.INVISIBLE);
        db= FirebaseFirestore.getInstance();


        //이미지 갤러리에서 가져오기
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            mSaveBtn.setText("Update");
            uName = bundle.getString("uName");
            uId = bundle.getString("uId");
            uLocation = bundle.getString("uLocation");
            uDate = bundle.getString("uDate");
            uProfileUri = bundle.getString("uProfileUri");
            mName.setText(uName);
            mLocation.setText(uLocation);
            mDate.setText(uDate);
            uProfileUri = mProfile.toString();

        }else{
            mSaveBtn.setText("Save");// 업데이트할 데이터가 없을 경우
        }


//        mShowBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MyMainActivity.this, Context.class));
//            }
//        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                uploadToFirebase(imageUri);
                Log.e("imageUri", imageUri.toString());
//                if (imageUri != null){
//
//                }else{
//                    Toast.makeText(MyMainActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
//                }
                String name = mName.getText().toString();
                String location = mLocation.getText().toString();
                String date = mDate.getText().toString();
                String profileUri = null;
                String userid = user.getUid();

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){
                    String id  = uId;
                    updateToFireStore(id, name, location, date);

                }else{

                    saveToFireStore(myplantid, name, location, date, profileUri, userid);

                }

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
    private void updateToFireStore(String id, String name, String location, String date){

        db.collection("Myplants").document(id).update("name", name, "location",location, "date", date)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MyMainActivity.this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                            uploadToFirebase(imageUri);
                        }else{
                            Toast.makeText(MyMainActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyMainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFireStore(String id , String name , String location, String date, String profileUri, String userid){

        if (!name.isEmpty() && !location.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , myplantid);
            map.put("profileUri" , null);
            map.put("name" , name);
            map.put("location" , location);
            map.put("date" , date);
            map.put("userID", userid);

            db.collection("Myplants").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MyMainActivity.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                                uploadToFirebase(imageUri);
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        StorageReference list = reference.child("Myplants/"+user.getUid());
//        list.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
//            @Override
//            public void onSuccess(ListResult listResult) {
//                List<StorageReference> a = listResult.getPrefixes();
//                listsize = String.valueOf(a.size()+1);
//                Log.e("listsize", listsize);
//            }
//        });

        Log.e("listsize2", listsize);

        StorageReference fileRef = reference.child("Myplants/"+user.getUid()+"/file"+listsize+".jpg");
        UploadTask uploadTask = fileRef.putFile(uri);
        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("이미지주소", uri.toString());
                db.collection("Myplants").document(myplantid)
                        .update("profileUri", uri.toString());
            }
        });
//        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//
//                        Model model = new Model(uri.toString());
//                        String modelId = root.push().getKey();
//                        root.child(modelId).setValue(model);
//                        progressBar2.setVisibility(View.INVISIBLE);
//
//                        Toast.makeText(MyMainActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                progressBar2.setVisibility(View.VISIBLE);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressBar2.setVisibility(View.INVISIBLE);
//                Toast.makeText(MyMainActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private String getfileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}
