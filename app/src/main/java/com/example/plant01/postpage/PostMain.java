package com.example.plant01.postpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.plant01.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PostMain extends AppCompatActivity {
    ImageButton search, more;
    FloatingActionButton fab;
    View.OnClickListener cl;
    Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_diary);

        search = (ImageButton) findViewById(R.id.search_button);
        more = (ImageButton) findViewById(R.id.more);
        fab = (FloatingActionButton) findViewById(R.id.add_post);

        i = getIntent();

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Post_write.class);
                startActivity(intent);
            }
        };
        fab.setOnClickListener(cl);
    }
}
