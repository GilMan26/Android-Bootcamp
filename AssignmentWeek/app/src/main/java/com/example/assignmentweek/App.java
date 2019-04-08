package com.example.assignmentweek;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.assignmentweek.Recievers.NetworkReciever;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkReciever reciever=new NetworkReciever();
        registerReceiver(reciever, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        );
    }
}
