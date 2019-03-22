package com.example.assignment7;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String DOWNLOAD_FILE_URL = "https://mars.jpl.nasa.gov/gallery/spacecraft/hires/phoenix_hr.jpg";

    private static final int PERMISSION_ASYNC = 001;
    private static final int PERMISSION_SERVICE = 002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ASYNC) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, DownloadAsyncActivity.class);
                intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Permissions not granted.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_SERVICE) { //when permission is taken from service button
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //all permissions granted
                Intent intent = new Intent(this, DownloadServiceActivity.class);
                intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Permissions not granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startAsync(View view) {
        //check permissions at run time
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {    //checking if device is android-23 or above
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //checking permissions manually and taking permissions if not granted already
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , PERMISSION_ASYNC);
            } else {
                Intent intent = new Intent(this, DownloadAsyncActivity.class);
                intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
                startActivity(intent);
            }
        } else {        //when device is below android-23
            Intent intent = new Intent(this, DownloadAsyncActivity.class);
            intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
            startActivity(intent);
        }

    }

    public void startService(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_SERVICE);
            } else {
                Intent intent = new Intent(this, DownloadServiceActivity.class);
                intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(this, DownloadServiceActivity.class);
            intent.putExtra("downloadUrl", DOWNLOAD_FILE_URL);
            startActivity(intent);
        }
    }
}
