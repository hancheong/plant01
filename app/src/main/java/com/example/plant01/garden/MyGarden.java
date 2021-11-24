package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.TypedArray;
import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;


public class MyGarden extends AppCompatActivity {

    TextView tvGarden;
    ImageButton ibnBack;
    Button btnAdd;
    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_my_garden);

        adapter = new CustomAdapter();
        listView = (ListView) findViewById(R.id.listView);
        tvGarden = (TextView) findViewById(R.id.tvGarden);
        ibnBack = (ImageButton) findViewById(R.id.ibnBack);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        setData();

        listView.setAdapter(adapter);

        ibnBack.setOnClickListener(new View.OnClickListener() {//뒤로가기 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeFragment.class);
                startActivity(intent);
            }
        });
    }
   private void setData() {
        TypedArray arrResId = getResources().obtainTypedArray(R.array.resId);
        String[] titles = getResources().getStringArray(R.array.title);
        String[] contents = getResources().getStringArray(R.array.content);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);
        }
    }
}