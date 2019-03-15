package com.example.assignment5.Ques4;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.assignment5.R;

public class MainActivity extends AppCompatActivity implements ListFragment.Communication {
    FragmentManager fragmentManager;
    ListFragment listFragment;
    OutputFragment outputFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        listFragment=new ListFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.listFragment, listFragment, null);
        fragmentTransaction.commit();
        outputFragment=new OutputFragment();
        FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.outputFragment, outputFragment, null);
        fragmentTransaction1.commit();

    }


    @Override
    public void communicate(Bundle  bundle) {
        OutputFragment outputFragment1= (OutputFragment) fragmentManager.findFragmentById(R.id.outputFragment);
        outputFragment1.getData(bundle);
    }
}
