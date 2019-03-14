package com.example.assignment5.Ques4;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.assignment5.R;

public class MainActivity extends AppCompatActivity implements ListFragment.Communication {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        ListFragment listFragment=new ListFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.listFragment, listFragment, null);
        fragmentTransaction.commit();
        OutputFragment outputFragment=new OutputFragment();
        FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.outputFragment, outputFragment, null);
    }


    @Override
    public void communicate(String string) {
//        outputFragment= (OutputFragment) fragmentManager.findFragmentById(R.id.outputFragment);
//        outputFragment.getData(string);
    }
}
