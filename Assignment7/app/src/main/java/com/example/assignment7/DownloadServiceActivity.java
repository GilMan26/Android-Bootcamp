package com.example.assignment7;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;

public class DownloadServiceActivity extends AppCompatActivity implements NetworkStateReciever.INetStateChange {
    ImageView imageView;
    String downloadUrl;
    ProgressDialog progressDialog;
    NetworkStateReciever NetworkStateReciever;
    public static final String FILE_NAME_SERVICE = "/ServiceImage.jpeg";

    private BroadcastReceiver getLocalBroadcastResult;
    private BroadcastReceiver getGetLocalBroadcastProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_async);
        imageView = findViewById(R.id.imageView);

        NetworkStateReciever.setConnectivityListener(this);
        NetworkStateReciever = new NetworkStateReciever();

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setMessage("Downloading Image");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        downloadUrl = getIntent().getStringExtra("downloadUrl");

        getLocalBroadcastResult = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bitmap bitmap = intent.getParcelableExtra("imageBitmap");
                if (bitmap == null)
                    Log.d("download-result", "Bitmap is null");
                imageView.setImageBitmap(bitmap);
            }
        };

        getGetLocalBroadcastProgress = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int progress = intent.getIntExtra("progress", 0);
                progressDialog.setProgress(progress);
                if (progress >= 100 && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(NetworkStateReciever, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        LocalBroadcastManager.getInstance(this).registerReceiver(getLocalBroadcastResult, new IntentFilter("downloadResult"));
        LocalBroadcastManager.getInstance(this).registerReceiver(getGetLocalBroadcastProgress, new IntentFilter("downloadProgress"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(NetworkStateReciever);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(getGetLocalBroadcastProgress);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(getLocalBroadcastResult);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file2 = new File(Environment.getExternalStorageDirectory(), FILE_NAME_SERVICE);

        boolean deletedFile2 = false;
        if (file2.exists())
            deletedFile2 = file2.delete();

        Log.d("MainActivity", "if deletedFile2 ?" + deletedFile2);
    }

    @Override
    public void mOnNetStateChangeListener(boolean isConnected) {
        if (isConnected) {
            DownloadService.stoppedService = false;
            Intent intent = new Intent(this, DownloadService.class);
            intent.putExtra("downloadUrl", downloadUrl);
            startService(intent);
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    DownloadService.stoppedService = true;
                }
            });
        } else {
            DownloadService.stoppedService = true;
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}
