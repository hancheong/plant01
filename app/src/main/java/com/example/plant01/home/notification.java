package com.example.plant01.home;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.plant01.R;

public class notification extends AppCompatActivity {
    private EditText mTitleText,mMessageText;
    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initViews();

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void btnOneClicked(View view) {

        String title=mTitleText.getText().toString();
        String message=mMessageText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        manager.notify(1,notification);

    }

    public void btnTwoClicked(View view) {

        String title=mTitleText.getText().toString();
        String message=mMessageText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        manager.notify(2,notification);

    }

    private void initViews() {
        mTitleText=findViewById(R.id.text_tile);
        mMessageText=findViewById(R.id.text_message);
    }
}