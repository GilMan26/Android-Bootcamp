package com.example.assignmentweek;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.assignmentweek.Recievers.NetworkReciever;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("app onc reate","in on create");
        NetworkReciever reciever=new NetworkReciever();
        registerReceiver(reciever, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        );
    }
}
