package com.example.assignment5.Ques3;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.assignment5.R;

public class ActivityQues3 extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques3);
        fragmentManager=getSupportFragmentManager();

    }

    public void showDialog(View view){
        DialogFragmentExample diag=new DialogFragmentExample();
        diag.show(fragmentManager,"Dialog Fragment");
    }

    public void showPreference(View view){
        PrefFragment prefFragment=new PrefFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frameLayout, prefFragment, "Preference Fragment");
        fragmentTransaction.commit();
    }
}
