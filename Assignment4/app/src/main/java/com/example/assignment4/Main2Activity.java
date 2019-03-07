package com.example.assignment4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView nameTV, phoneTV, emailTV;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nameTV=findViewById(R.id.nameText);
        phoneTV=findViewById(R.id.phoneText);
        emailTV=findViewById(R.id.emailText);
        imageView=findViewById(R.id.imageView);
        Intent intent=getIntent();
        Bundle dataBundle=intent.getExtras();
        String name=dataBundle.getString("name");
        String email=dataBundle.getString("email");
        String phone=dataBundle.getString("phone");
        Bitmap image=dataBundle.getParcelable("image");
        nameTV.setText(name);
        phoneTV.setText(phone);
        emailTV.setText(email);
        imageView.setImageBitmap(image);
    }
}
