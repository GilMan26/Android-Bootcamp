package com.example.assignment7;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadFileService";
    public static volatile boolean stoppedService = false;  // false to run the service

    public DownloadService() {
        super("DownloadFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String downloadUrl = intent.getStringExtra("downloadUrl");

        InputStream inputStream;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Bitmap resultBitmap;

        float progress;
        long downloaded;
        try {
            URL url = new URL(downloadUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            File file = new File(Environment.getExternalStorageDirectory(),
                    DownloadServiceActivity.FILE_NAME_SERVICE);


            long fileSizeToDownload = url.openConnection().getContentLength();
            Log.d(TAG, "fileSizeToDownload " + String.valueOf(fileSizeToDownload));


            Log.d(TAG, "downloaded " + String.valueOf(file.length()));
            downloaded = file.length();

            if (downloaded != 0 && downloaded < fileSizeToDownload) {
                Log.d(TAG, "resuming file downloading from" + file.length());

                httpURLConnection.setRequestProperty("Range", "bytes=" + downloaded + "-");
            }
            else if (file.length() == fileSizeToDownload) {
                Log.d(TAG, "file size downloaded is " + file.length() + " fileSizeToDownload is " + fileSizeToDownload);

                resultBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                Intent intentSetProgress = new Intent("downloadProgress");
                intentSetProgress.putExtra("progress", 100);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
                Intent intentSetResult = new Intent("downloadResult");
                intentSetResult.putExtra("imageBitmap", resultBitmap);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetResult);
                stoppedService = true;
                stopSelf();
                return;
            } else {
                boolean b = file.createNewFile();
                Log.d(TAG, "created new file " + b);
            }

            httpURLConnection.connect();

            long remainingFileSizeToDownload = httpURLConnection.getContentLength();
            inputStream = httpURLConnection.getInputStream();

            if (downloaded > 0) {
                outputStream = new FileOutputStream(file, true);
                progress = (float) (downloaded * 100) / fileSizeToDownload;
            } else {
                outputStream = new FileOutputStream(file, false);
                progress = 0f;

                Intent intentSetProgress = new Intent("downloadProgress");
                intentSetProgress.putExtra("progress", (int) progress);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
            }

            byte[] data = new byte[1024];
            int receivedKB;
            long totalReceivedKB = 0;

            while ((receivedKB = inputStream.read(data)) != -1) {
                if (stoppedService) {
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d(TAG, "download cancelled");

                    Intent intentSetProgress = new Intent("downloadProgress");
                    intentSetProgress.putExtra("progress", 100);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
                    stopSelf();//Stopping service execution
                    return;
                } else {
                    outputStream.write(data, 0, receivedKB);
                    totalReceivedKB = totalReceivedKB + receivedKB;
                    Intent intentSetProgress = new Intent("downloadProgress");
                    intentSetProgress.putExtra("progress", (int) (progress + (float) (totalReceivedKB * 100) / fileSizeToDownload));
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
                }
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d(TAG, "Downloaded file size:" + file.length());
            resultBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            Intent intentSetResult = new Intent("downloadResult");
            intentSetResult.putExtra("imageBitmap", resultBitmap);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetResult);
            stopSelf();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
