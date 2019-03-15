package com.example.assignment5.Ques2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assignment5.R;

public class ActivityQues2 extends AppCompatActivity {
    Button add1, replace1, remove1, add2, replace2, remove2;
    FragmentManager fragmentManager;
    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques2);
        add1=findViewById(R.id.add1);
        replace1=findViewById(R.id.replace1);
        remove1=findViewById(R.id.remove1);
        add2=findViewById(R.id.add2);
        replace2=findViewById(R.id.replace2);
        remove2=findViewById(R.id.remove2);
        fragmentManager=getSupportFragmentManager();

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment1=new Fragment1();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(fragment1, "fragment1");
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment2=new Fragment2();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(fragment2, "fragment2");
            }
        });

        replace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment2, fragment1);
            }
        });

        replace2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment1, fragment2);
            }
        });

        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment1);
            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment1);
            }
        });
    }
}
