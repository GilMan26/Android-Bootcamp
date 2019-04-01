package com.example.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MyFcmService extends FirebaseMessagingService {
    NotificationManager notificationManager;
    public static final String CHANNEL_ID = "push_notif_id";
    public static final String CHANNEL_NAME = "push_notif_name";
    public static final String TAG = "MyFcmService";
    String url, value;
    Bitmap imageBitmap;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, remoteMessage.getData().get("image"));
        url = remoteMessage.getData().get("image");
        value = remoteMessage.getData().get("price");
        //creating notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Channel for push notifications");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        sendNotifications();

    }

    public void sendNotifications() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("value", value);
        intent.putExtra("url", url);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 201, intent, PendingIntent.FLAG_ONE_SHOT);
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
        notificationBuilder.setContentTitle("Push Notification");
        notificationBuilder.setContentIntent(pendingIntent);
        Glide.with(getApplicationContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource));
                notificationManager.notify(101, notificationBuilder.build());
            }
        });
    }
}

