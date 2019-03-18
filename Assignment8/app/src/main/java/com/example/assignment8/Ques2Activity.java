package com.example.assignment8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class Ques2Activity extends AppCompatActivity {
    public static final String CHANNEL_LOW="channelLow";
    public static final String CHANNEL_HIGH="channelHigh";
    NotificationManager notificationManager;
    Button buttonHigh;
    NotificationCompat.Builder lowBuilder, highBuilder;
    boolean loop=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques2);
        buttonHigh=findViewById(R.id.buttonHIgh);
        notificationManager=getSystemService(NotificationManager.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channelLow=new NotificationChannel(CHANNEL_LOW, "Channel Low", NotificationManager.IMPORTANCE_LOW);
            channelLow.setDescription("Channel for Low priority notificaions");

            NotificationChannel channelHigh=new NotificationChannel(CHANNEL_HIGH, "Channel High", NotificationManager.IMPORTANCE_HIGH);
            channelHigh.setDescription("Channel for High priority notifications ");
            channelHigh.setVibrationPattern(new long[] {100, 200, 300, 400, 500});

            notificationManager.createNotificationChannel(channelHigh);
            notificationManager.createNotificationChannel(channelLow);
        }
        createNotifications();

        buttonHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotif();
                blockNotif();
            }
        });



    }

    public void showNotif(){
        highThread.start();
    }

    public void blockNotif() {
        Timer timer = new Timer ();
        TimerTask minuteTask = new TimerTask () {
            @Override
            public void run () {
//                StatusBarNotification[] notifications = notificationManager.getActiveNotifications();
//                for (StatusBarNotification notification : notifications) {
//                    if (notification.getId() == 101) {
//                        notificationManager.cancel(101);
//                        notificationManager.notify(102, highBuilder.build());
//                    } else {
//                        notificationManager.cancel(102);
//                        notificationManager.notify(101, lowBuilder.build());
//                    }
//                }
                if(loop==true){
                    notificationManager.notify(102,highBuilder.build());
                    loop=false;
                }
                else{
                    notificationManager.notify(101,lowBuilder.build());
                    loop=true;
                }
            }
        };
        timer.schedule (minuteTask,0 , 1000*60);

    }

    Thread highThread=new Thread(new Runnable() {
        @Override
        public void run() {
            notificationManager.notify(101, lowBuilder.build());
        }
    });

    Thread lowThread=new Thread(new Runnable() {
        @Override
        public void run() {
            notificationManager.notify(102, highBuilder.build());
        }
    });

    public void createNotifications(){
        lowBuilder=new NotificationCompat.Builder(Ques2Activity.this, CHANNEL_HIGH)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentTitle("High Priority")
                .setContentText("High priority Notification")
                .setSmallIcon(R.drawable.ic_settings_system_daydream_black_24dp);
//        notificationManager.notify(102, lowBuilder.build());

        highBuilder=new NotificationCompat.Builder(Ques2Activity.this, CHANNEL_LOW)
                .setPriority(Notification.PRIORITY_LOW)
                .setContentTitle("Low Priority")
                .setContentText("Low priority Notification")
                .setSmallIcon(R.drawable.ic_settings_system_daydream_black_24dp);
//        notificationManager.notify(101, highBuilder.build());
    }
}
