package com.example.assignment4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity {
    private final static int CAMERA_CODE=101;
    private final static int CAMERA_REQUEST=102;
    EditText nameEditText, phoneEditText, emailEditText;
    Button photoButton, submitButton;
    Bitmap image;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText=findViewById(R.id.nameEditText);
        phoneEditText=findViewById(R.id.phoneEditText);
        emailEditText=findViewById(R.id.emailEditText);
        photoButton=findViewById(R.id.captureButton);
        submitButton=findViewById(R.id.submitButton);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);

                } else {
                    Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }

        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                String name=nameEditText.getText().toString();
                String phone=phoneEditText.getText().toString();
                String email=emailEditText.getText().toString();
                bundle.putString("name", name);
                bundle.putString("phone", phone);
                bundle.putString("email", email);
                bundle.putParcelable("image", image);
                Intent submitIntent=new Intent(MainActivity.this, Main2Activity.class);
                submitIntent.putExtras(bundle);
                startActivity(submitIntent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
            image = (Bitmap) data.getExtras().get("data");
            Toast.makeText(this, "Image captured ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Camera Permission Granted!", Toast.LENGTH_SHORT).show();
                Intent captureIntent = new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_REQUEST);
            }
            else {
                Toast.makeText(MainActivity.this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
