package com.example.plant01.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.plant01.R;

public class noticebar extends AppCompatActivity {

    public   void createNotification(View view){
        show();
    }
    private  void show(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.store_icon);
        builder.setContentTitle("온도경고");
        builder.setContentText("온도가 너무 높아요! 확인해주세요");

        Intent intent = new Intent(this, noticebar.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.mipmap.store_icon);
        builder.setLargeIcon(largeIcon);
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        long[] vibrate = {0,100,200,300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(new NotificationChannel("default", "기본채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1, builder.build());
    }

    public void removeNotification(View view){

    }


}
