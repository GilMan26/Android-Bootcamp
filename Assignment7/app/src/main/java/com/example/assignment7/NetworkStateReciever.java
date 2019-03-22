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
            Toast.makeText(context, "Network Available", Toast.LENGTH_SHORT).show();
            iNetStateChange.mOnNetStateChangeListener(true);
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
            iNetStateChange.mOnNetStateChangeListener(false);
        }
    }


    public static void setConnectivityListener(NetworkStateReciever.INetStateChange iNetStateChangeOb) {
        iNetStateChange = iNetStateChangeOb;
    }

    public interface INetStateChange {
        void mOnNetStateChangeListener(boolean isConnected);
    }
}
