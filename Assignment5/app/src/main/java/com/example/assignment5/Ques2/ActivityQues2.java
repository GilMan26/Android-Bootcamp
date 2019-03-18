package com.example.assignment5.Ques2;

import android.support.v4.app.Fragment;
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
                fragmentTransaction.add(R.id.fragment_container, fragment1);
                fragmentTransaction.addToBackStack("Fragment1");
                fragmentTransaction.commit();
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment2=new Fragment2();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, fragment2);
                fragmentTransaction.addToBackStack("Fragment2");
                fragmentTransaction.commit();
            }
        });

        replace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction = fragmentManager.beginTransaction();

                Fragment1 frg1 = new Fragment1();
                Fragment2 frg2 = new Fragment2();

                if(fragment instanceof Fragment1){
                    fragmentTransaction.replace(R.id.fragment_container,frg2);
                    fragmentTransaction.commit();
                }
                else if (fragment instanceof Fragment2){
                    fragmentTransaction.replace(R.id.fragment_container, frg1);
                    fragmentTransaction.commit();
                }
            }
        });

        replace2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_out, android.R.animator.fade_in)
                        .hide(fragment).commit();
            }
        });

        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(fragment).commit();
            }
        });
    }
}
