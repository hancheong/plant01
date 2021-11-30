package com.example.plant01.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.plant01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchResult extends AppCompatActivity {
    private FirebaseFirestore db;
    ListView tiplistView;
    String[] tips;
    RoundedImageView plantImg;
    Toolbar toolbar;

    public SearchResult() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        toolbar = findViewById(R.id.search_result_toolbar);

        setSupportActionBar(toolbar);
        setTitle("검색결과");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        plantImg = (RoundedImageView)findViewById(R.id.result_plant_img);

//        tiplistView = findViewById(R.id.tipListview);
//        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
//            @NonNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        }

//        tips = new String[]{};
        recivetip();
//        Log.e("String[] ",Arrays.toString(tips));

//
////        for(int i=0; i< list.size(); i++){
////                            Log.e("TEST", "data["+i+"] > " + list.get(i).toString());
//////                            tips[i] = list.get(i).toString();
////                        }
//////        /*----Tips스트링 가져오기*/
//        ArrayAdapter<String> tipAdapter = new ArrayAdapter<>(this, R.layout.result_tip_item,R.id.tip,tips);
//        tiplistView.setAdapter(tipAdapter);


        /*-------파이어스토어와 연결----*/


//        Query plantinfo = db.collection("Plant").whereEqualTo("plantName", getplantname);
//
//        plantinfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        ArrayList list = (ArrayList)document.getData().get("plantTip");
//                        tips = new String[list.size()];
//                        //Convert to ArrayList
////                        List<String> testList = new ArrayList<>(Arrays.asList(t));
//                        for(int i=0; i< list.size(); i++){
////                            Log.e("TEST", "data["+i+"] > " + list.get(i).toString());
//                            tips[i] = list.get(i).toString();
//                        }
//                        String plantImgurl = (String) document.get("plantImg");
//                        Log.e("식물테이블", plantImgurl);
//                        Glide.with(SearchResult.this)
//                                .load(Uri.parse(plantImgurl))
//                                .into(plantImg);
//                    }
//                }
//            }
//        });

    }

    public void recivetip(){
        /*-------분석에서 결과 받아오기 ------*/
        tiplistView = findViewById(R.id.tipListview);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent(); /*데이터 수신*/
        String getplantname = intent.getExtras().getString("plantName");
        Query plantinfo = db.collection("Plant").whereEqualTo("plantName", getplantname);

        plantinfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public  void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ArrayList list = (ArrayList) document.getData().get("plantTip");
                        String[] dbtips = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
//                            Log.e("TEST", "data["+i+"] > " + list.get(i).toString());
                            dbtips[i] = list.get(i).toString();
                        }
                        Log.e("String[] ", Arrays.toString(dbtips));
                        ArrayAdapter tipAdapter = new ArrayAdapter(SearchResult.this, R.layout.result_tip_item,R.id.tip,dbtips);
                        tiplistView.setAdapter(tipAdapter);
                    }

                }

            }
        });
    }

//    public String[] returnString(){
//        db = FirebaseFirestore.getInstance();
//        final String[] dbtips;
//        Intent intent = getIntent(); /*데이터 수신*/
//        String getplantname = intent.getExtras().getString("plantName");
//        Query plantinfo = db.collection("Plant").whereEqualTo("plantName", getplantname);
//
//        plantinfo.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public  void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        ArrayList list = (ArrayList) document.getData().get("plantTip");
//                         dbtipas = new String[list.size()];
////                        Convert to ArrayList
////                        List<String> testList = new ArrayList<>(Arrays.asList(t));
//                        for (int i = 0; i < list.size(); i++) {
////                            Log.e("TEST", "data["+i+"] > " + list.get(i).toString());
//                            dbtips[i] = list.get(i).toString();
//                        }
//
//                    }
//                }
//            }
//        });
//        return dbtips;
//    }
}