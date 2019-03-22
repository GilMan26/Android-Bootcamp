package com.example.assignment7;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAsyncActivity extends AppCompatActivity implements NetworkStateReciever.INetStateChange {
    ImageView imageView;
    ProgressDialog progressDialog;
    public static final String FILE_NAME_ASYNC = "/AsyncImage.jpg";
    NetworkStateReciever reciever;
    boolean resumeDownload = true;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_async);

        imageView = findViewById(R.id.imageView);
        NetworkStateReciever.setConnectivityListener(this);
        reciever=new NetworkStateReciever();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Downloading Image");

        url = getIntent().getStringExtra("downloadUrl");
    }


    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(reciever, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

    }
    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(reciever);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file1 = new File(Environment.getExternalStorageDirectory(), FILE_NAME_ASYNC);
        boolean deletedFile1 = false;
        if (file1.exists())
            deletedFile1 = file1.delete();
    }

    @Override
    public void mOnNetStateChangeListener(boolean value) {
        if (value && resumeDownload) {
            Toast.makeText(DownloadAsyncActivity.this, "Network Available", Toast.LENGTH_LONG).show();
            new DownloadImageTask().execute(url);
        } else {
            Toast.makeText(this, "Download cancelled.", Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancel(true);
                }
            });

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            HttpURLConnection urlConnection=null;
            InputStream inputStream=null;
            OutputStream outputStream=null;
            Bitmap bitmap=null;
            int progress=0;

            try {
                URL url=new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME_ASYNC);
                long downloaded = 0;

                long downloadFileSize = url.openConnection().getContentLength();

                if (file.exists() && file.length() < downloadFileSize) {
                    downloaded = file.length();
                    urlConnection.setRequestProperty("Range", "bytes=" + downloaded + "-");
                } else if (file.exists() && file.length() == downloadFileSize) {
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    return bitmap;
                } else {
                    boolean ifcreatedFile = file.createNewFile();
                }

                urlConnection.connect();
                inputStream=urlConnection.getInputStream();

                if (downloaded > 0 && downloaded < downloadFileSize) {
                    outputStream = new FileOutputStream(file, true);
                    progress = (int) (downloaded * 100 / downloadFileSize);
                } else
                    outputStream = new FileOutputStream(file);

                byte[] data = new byte[512];
                int receivedKB;
                long totalReceivedKB = 0;

                while ((receivedKB = inputStream.read(data)) != -1) {
                    if (isCancelled()) {
                        resumeDownload = false;
                        break;
                    } else {
                        outputStream.write(data, 0, receivedKB);
                        totalReceivedKB = totalReceivedKB + receivedKB;
                        publishProgress(progress + (int) (totalReceivedKB * 100 / downloadFileSize));
                    }

                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
                urlConnection.disconnect();
                bitmap=BitmapFactory.decodeFile(file.getAbsolutePath());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);

        }


        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
            Toast.makeText(DownloadAsyncActivity.this, "Download cancelled.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }



}
