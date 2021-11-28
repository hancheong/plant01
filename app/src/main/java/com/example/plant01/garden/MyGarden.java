package com.example.plant01.garden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.TypedArray;
import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;


public class MyGarden extends Fragment {

    TextView tvGarden;
    ImageButton ibnBack;
    Button btnAdd;
    ListView listView;
    CustomAdapter adapter;

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.garden_my_garden);

        View view = inflater.inflate(R.layout.garden_my_garden, container, false);

        adapter = new CustomAdapter();
        listView = (ListView) view.findViewById(R.id.listView);
        tvGarden = (TextView) view.findViewById(R.id.tvGarden);
        ibnBack = (ImageButton) view.findViewById(R.id.ibnBack);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);

        setData();

        listView.setAdapter(adapter);

        ibnBack.setOnClickListener(new View.OnClickListener() {//뒤로가기 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HomeFragment.class);
                startActivity(intent);
            }
        });
        return view;
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