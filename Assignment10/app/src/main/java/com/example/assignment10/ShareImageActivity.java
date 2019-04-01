package com.example.assignment10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ShareImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
    }

    public void shareInternal(View view){
        try {
            //storing dumy file into internal storage area
            Bitmap bitmapInternal = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
            File internalRootDirectory = getFilesDir();
            File internalSavingDir = new File(internalRootDirectory, "Image1");
            if (!internalSavingDir.exists())
                internalSavingDir.mkdirs();

            File fileInternal = new File(internalSavingDir, "image1.jpg");
            if (fileInternal.exists()) {
                fileInternal.delete();
            }
            fileInternal.createNewFile();
            OutputStream outputStreamInternal = new FileOutputStream(fileInternal);
            bitmapInternal.compress(Bitmap.CompressFormat.JPEG, 25, outputStreamInternal);
            outputStreamInternal.flush();
            outputStreamInternal.close();

            //sharing file using file provider
            Intent shareIntentInternal = new Intent();
            shareIntentInternal.setAction(Intent.ACTION_SEND);
            shareIntentInternal.setType("image/*");
            shareIntentInternal.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileInternal));
            shareIntentInternal.putExtra(Intent.EXTRA_TEXT, "Sharing using app's internal space");
            shareIntentInternal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntentInternal, "Share using"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shareExteranal(View view){
        try {
            //storing dumy file into external storage area
            Bitmap bitmapExternal = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
            File externalRootDirectory = Environment.getExternalStorageDirectory();
            File externalSavingDir = new File(externalRootDirectory, "dumy_images");
            if (!externalSavingDir.exists())
                externalSavingDir.mkdirs();
            File fileExternal = new File(externalSavingDir, "image2.jpg");
            if (fileExternal.exists()) {
                fileExternal.delete();
            }
            fileExternal.createNewFile();
            OutputStream outputStreamExternal = new FileOutputStream(fileExternal);
            bitmapExternal.compress(Bitmap.CompressFormat.JPEG, 25, outputStreamExternal);
            outputStreamExternal.flush();
            outputStreamExternal.close();

            //sharing file using fileprovider
            Intent shareIntentExternal = new Intent();
            shareIntentExternal.setAction(Intent.ACTION_SEND);
            shareIntentExternal.setType("image/*");
            shareIntentExternal.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileExternal));
            shareIntentExternal.putExtra(Intent.EXTRA_TEXT, "Sharing using app's external space");
            shareIntentExternal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntentExternal, "Share using"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
