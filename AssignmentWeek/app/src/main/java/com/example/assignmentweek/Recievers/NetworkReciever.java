package com.example.assignmentweek.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

public class NetworkReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connMgr.getActiveNetworkInfo();

        if (wifi!=null && wifi.isConnectedOrConnecting()) {
            Toast.makeText(context, "Network Connected", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_LONG).show();
    }

}
