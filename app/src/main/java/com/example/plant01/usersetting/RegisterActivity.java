package com.example.plant01.usersetting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;  //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail;
    private EditText mEtPwd;
    private String mEtPh;
    private String mEtPostcode;
    private String mEtBirth;
    private String mEtNick;
    private Button mBtnRegister;
    public  String UserGender;
    private RadioGroup mRgGender;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("PlanT");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);

        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(onClickListener);


        mRgGender = (RadioGroup)findViewById(R.id.rg_gender);
        int mEtGenderID = mRgGender.getCheckedRadioButtonId();
        UserGender =((RadioButton)findViewById(mEtGenderID)).getText().toString();
        mRgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                UserGender = genderButton.getText().toString();
            }
        });
        mEtNick = ((EditText)findViewById(R.id.et_nick)).getText().toString();
        mEtPh = ((EditText) findViewById(R.id.et_ph)).getText().toString();
        mEtPostcode = ((EditText) findViewById(R.id.et_postcode)).getText().toString();
        mEtBirth = ((EditText) findViewById(R.id.et_birth)).getText().toString();



    }

    View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btn_register:
                    register();
                    break;
            }
        }
    };

    private void register(){
        String strEmail = mEtEmail.getText().toString();
        String strPwd = mEtPwd.getText().toString();

        //firebase auth진행
        mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                    UserAccount account = new UserAccount();
                    account.setIdToken(firebaseUser.getUid());
                    account.setUserEmail(firebaseUser.getEmail());
                    account.setUserPassword(strPwd);
                    account.setUserBirth(mEtBirth);
//                    account.setUserNick(mEtNick);
//                    account.setUserPh(mEtPh);
//                    account.setUserPostalCode(mEtPostcode);

                    //setvalue 데이터베이스에 삽입하는 행위
                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                    Log.e("성공","성공");

                    Toast.makeText(RegisterActivity.this,"회원가입 성공", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(RegisterActivity.this,"회원가입실패", Toast.LENGTH_SHORT).show();
                    Log.e("실패","실패");

                }
            }
        });
    }

//   public void enterUser(){
//       mRgGender = (RadioGroup)findViewById(R.id.rg_gender);
//       int mEtGenderID = mRgGender.getCheckedRadioButtonId();
//       UserGender =((RadioButton)findViewById(mEtGenderID)).getText().toString();
//       mRgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//           @Override
//           public void onCheckedChanged(RadioGroup group, int i) {
//               RadioButton genderButton = (RadioButton) findViewById(i);
//               UserGender = genderButton.getText().toString();
//           }
//       });
//       mEtNick = ((EditText)findViewById(R.id.et_nick)).getText().toString();
//       mEtPh = ((EditText) findViewById(R.id.et_ph)).getText().toString();
//       mEtPostcode = ((EditText) findViewById(R.id.et_postcode)).getText().toString();
//       mEtBirth = ((EditText) findViewById(R.id.et_birth)).getText().toString();
//
//       //유저의 토큰아이디와 다른 정보들을 저장장
//      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//       mDatabaseRef = FirebaseDatabase.getInstance().getReference();
//
//       User2
//
//
//   }
}