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
    public static volatile boolean stoppedService = false;  //it is required to be false to run the service

    public DownloadService() {
        super("DownloadFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //obtaining download url
        String downloadUrl = intent.getStringExtra("downloadUrl");

        //initializing streams
        InputStream inputStream;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Bitmap resultBitmap = null;     //initializing bitmap

        float progress;   //initializing progress as 0 for progress dialog
        long downloaded;    //it stores the downloaded file size
        try {
            URL url = new URL(downloadUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            //creating temporary file in user's device to store image
            File file = new File(Environment.getExternalStorageDirectory(),
                    DownloadServiceActivity.FILE_NAME_SERVICE);


            long fileSizeToDownload = url.openConnection().getContentLength();      //size of file to be downloaded
            Log.d(TAG, "fileSizeToDownload " + String.valueOf(fileSizeToDownload));


            Log.d(TAG, "downloaded " + String.valueOf(file.length()));
            downloaded = file.length();     //size of the file existing locally

            //checking if file exists, if it exists then customizing connection to implement resume of file
            if (downloaded != 0 && downloaded < fileSizeToDownload) {
                Log.d(TAG, "resuming file downloading from" + file.length());

                //setting download to resume ahead of already downloaded length
                httpURLConnection.setRequestProperty("Range", "bytes=" + downloaded + "-");
            }   //checking if file was completely downloaded?
            else if (file.length() == fileSizeToDownload) {
                Log.d(TAG, "file size downloaded is " + file.length() + " fileSizeToDownload is " + fileSizeToDownload);

                //If file is already downloaded completely, sending result back to the calling activity using broadcast
                resultBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                //sending progress
                Intent intentSetProgress = new Intent("downloadProgress");
                intentSetProgress.putExtra("progress", 100);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
                //sending result bitmap
                Intent intentSetResult = new Intent("downloadResult");
                intentSetResult.putExtra("imageBitmap", resultBitmap);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetResult);
                //stopping service
                stoppedService = true;
                stopSelf();
                return;
            } else {    //if file doesnot exists, creating one new file
                boolean b = file.createNewFile();
                Log.d(TAG, "created new file " + b);
            }

            //establishing connection after setting properities
            httpURLConnection.connect();

            //obtaining size of file to be downloaded
            long remainingFileSizeToDownload = httpURLConnection.getContentLength();
            inputStream = httpURLConnection.getInputStream();

            //setting progress bar progress initial
            if (downloaded > 0) {
                //creating outputstream to restore file download from previous progress
                outputStream = new FileOutputStream(file, true);
                //restoring progress status on progressbar
                progress = (float) (downloaded * 100) / fileSizeToDownload;
            } else {
                //create new output stream
                outputStream = new FileOutputStream(file, false);
                progress = 0f;

                Intent intentSetProgress = new Intent("downloadProgress");
                intentSetProgress.putExtra("progress", (int) progress);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
            }

            //creating buffer of one kB
            byte[] data = new byte[1024];
            int receivedKB;
            long totalReceivedKB = 0;

            while ((receivedKB = inputStream.read(data)) != -1) {
                if (stoppedService) {
                    //if service is explicitely flagged to stop its execution
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d(TAG, "download cancelled");

                    //sending 100 progress so that progress bar get dismissed immediately
                    Intent intentSetProgress = new Intent("downloadProgress");
                    intentSetProgress.putExtra("progress", 100);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentSetProgress);
                    stopSelf();//Stopping service execution
                    return;
                } else {
                    //if service is not flagged to stop
                    outputStream.write(data, 0, receivedKB);
                    totalReceivedKB = totalReceivedKB + receivedKB;
                    //sending progress of download back to the calling activity
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

            //sending result bitmap to activity using the broadcast
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
