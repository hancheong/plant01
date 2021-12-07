package com.example.plant01.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.plant01.R;
import com.example.plant01.garden.MyPlants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {
    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi;
    FirebaseDatabase db;
    String maxTemper, minTemper;
    String myPlantID;
    DatabaseReference databaseReference;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    public void gedtaa(){
//
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        myServiceHandler handler = new myServiceHandler();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("MyDevice");
        databaseReference.child("aksjcnejas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxTemper = dataSnapshot.child("temperMaxGap").getValue().toString();
                minTemper = dataSnapshot.child("temperMinGap").getValue().toString();
                myPlantID = (String) dataSnapshot.child("myPlantID").getValue();

                Log.e("Myservice", String.valueOf(maxTemper));
                if(Double.parseDouble(maxTemper) < 0.0 ){
                    thread = new ServiceThread(handler);
                    thread.start();
                }
                else if (Double.parseDouble(minTemper) < 0.0){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


//        databaseReference.child("aksjcnejas").child("temperMaxGap").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                maxTemper = dataSnapshot.getValue().toString();
//                Log.e("Myservice", String.valueOf(maxTemper));
//                if(Double.parseDouble(maxTemper) < 0.0 ){
//                    myServiceHandler handler = new myServiceHandler();
//                    thread = new ServiceThread(handler);
//                    thread.start();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });





        return START_STICKY;
    } //서비스가 종료될 때 할 작업

    public void onDestroy() {
//        thread.stopForever();
        thread = null;

//
//        출처: https://twinw.tistory.com/50 [흰고래의꿈]
//        myServiceHandler handler = new myServiceHandler();
//        thread = new ServiceThread(handler);
//        thread.start();
    }

    public void start() {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
    }

    public void stop() {
        myServiceHandler handler = new myServiceHandler();

        thread = new ServiceThread(handler);
        thread.stopForever();
    }

    public class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, "default");
            builder.setSmallIcon(R.mipmap.store_icon);
            builder.setContentTitle("온도경고");
            builder.setContentText("온도가 너무 높아요! 확인해주세요");

            Intent intent = new Intent(MyService.this, MyPlants.class);
            intent.putExtra("myplantid", myPlantID);
//            startActivity(intent);
            Log.e("myplantid", myPlantID);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this,10,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.store_icon);
            builder.setLargeIcon(largeIcon);
            Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(MyService.this, RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(ringtoneUri);

            long[] vibrate = {0,100,200,300};
            builder.setVibrate(vibrate);
            builder.setAutoCancel(true);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                manager.createNotificationChannel(new NotificationChannel("default", "기본채널", NotificationManager.IMPORTANCE_DEFAULT));
            }
            manager.notify(10, builder.build());
            thread.stopForever();
//            assert manager != null;

//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            Intent intent = new Intent(MyService.this, bell.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel("my_notification", "n_channel", NotificationManager.IMPORTANCE_MAX);
//                notificationChannel.setDescription("description");
//                notificationChannel.setName("Channel Name");
//                assert notificationManager != null;
//                notificationManager.createNotificationChannel(notificationChannel);
//            }
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyService.this)
//                    .setSmallIcon(R.drawable.appicon)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.appicon))
//                    .setContentTitle("Title").setContentText("ContentText")
//                    .setAutoCancel(true).setSound(soundUri)
//                    .setContentIntent(pendingIntent)
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setOnlyAlertOnce(true).setChannelId("my_notification")
//                    .setColor(Color.parseColor("#ffffff")); //
//            // .setProgress(100,50,false);
//            assert notificationManager != null;
////            int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
//            Calendar cal = Calendar.getInstance(); int hour = cal.get( Calendar.HOUR_OF_DAY );
//            if (hour == 18) { notificationManager.notify( m, notificationBuilder.build() );
//            thread.stopForever(); } else if (hour == 22) { notificationManager.notify( m, notificationBuilder.build() );
//            thread.stopForever(); } } } }
        }
    }
}


//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////
////        db = FirebaseDatabase.getInstance();
////        databaseReference = db.getReference("MyDevice");
////
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
////        databaseReference.child("aksjcnejas").child("temperMaxGap").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                maxTemper = dataSnapshot.getValue().toString();
////                Log.e("value", String.valueOf(maxTemper));
////                if(Double.parseDouble(maxTemper) < 0.0 ){
////
////                }
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
////
////        });
////        databaseReference.child("aksjcnejas").child("temperMinGap").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                minTemper = dataSnapshot.getValue().toString();
//////                Log.e("value", value);
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
////
////        });
//
//        myServiceHandler handler = new myServiceHandler();
//        thread = new ServiceThread(handler);
//        thread.start();
//        return START_STICKY;
//    }
//
//    //서비스가 종료될 때 할 작업
//
//    public void onDestroy() {
//        thread.stopForever();
//        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
//    }
//
//    class myServiceHandler extends Handler {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            Intent intent = new Intent(MyService.this, noticebar.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//            Notifi = new Notification.Builder(getApplicationContext())
//                    .setContentTitle("Content Title")
//                    .setContentText("Content Text")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setTicker("알림!!!")
//                    .setContentIntent(pendingIntent)
//                    .build();
//
//            //소리추가
//            Notifi.defaults = Notification.DEFAULT_SOUND;
//
//            //알림 소리를 한번만 내도록
//            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;
//
//            //확인하면 자동으로 알림이 제거 되도록
//            Notifi.flags = Notification.FLAG_AUTO_CANCEL;
//
//
//            Notifi_M.notify( 777 , Notifi);
//
//            //토스트 띄우기
//            Toast.makeText(MyService.this, "뜸?", Toast.LENGTH_LONG).show();
//        }
//    };
//}


