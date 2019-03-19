package com.example.assignment9;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HTTPAsyncTask extends AsyncTask<String, Void , String> {

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader =null;

        try {
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((s = bufferedReader.readLine())!=null ){

                stringBuffer.append(s);


            }
            return String.valueOf(stringBuffer);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
