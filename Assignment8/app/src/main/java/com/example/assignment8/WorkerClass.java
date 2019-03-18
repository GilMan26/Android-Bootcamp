package com.example.assignment8;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerClass extends Worker {
    NotificationManager notificationManager;
    String NOTIF_ID="Worker Notification";

    public WorkerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        notificationManager= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(new NotificationChannel(NOTIF_ID,"Worker Channel", NotificationManager.IMPORTANCE_DEFAULT));

            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),NOTIF_ID)
                    .setSmallIcon(R.drawable.ic_settings_system_daydream_black_24dp)
                    .setContentText("This is a workker notificaiton")
                    .setContentTitle("Worker Notification");

            notificationManager.notify(10, builder.build());
            return Result.success();
        }
        return Result.success();
    }
}
