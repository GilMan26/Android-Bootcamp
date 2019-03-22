package com.example.assignment7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkStateReciever extends BroadcastReceiver {
    static INetStateChange iNetStateChange;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            Toast.makeText(context, "Internet is connected.", Toast.LENGTH_SHORT).show();
            iNetStateChange.mOnNetStateChangeListener(true);
        } else {
            Toast.makeText(context, "Internet is disconnected.", Toast.LENGTH_SHORT).show();
            iNetStateChange.mOnNetStateChangeListener(false);
        }
    }


    public static void setConnectivityListener(NetworkStateReciever.INetStateChange iNetStateChangeOb) {
        //this method is used to instantiate connectivity listener
        iNetStateChange = iNetStateChangeOb;
    }

    //using interface as bridge to communicate
    public interface INetStateChange {
        //customized method to listen network connectivity events
        void mOnNetStateChangeListener(boolean isConnected);
    }
}
