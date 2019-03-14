package com.example.assignment5.Ques1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.assignment5.R;

public class FragmentLifeCycle1 extends AppCompatActivity {


/*
2019-03-14 22:54:55.266 27015-27015/com.example.assignment5 D/Lifecycle: In onAttach
2019-03-14 22:54:55.266 27015-27015/com.example.assignment5 D/Lifecycle: In onCreate
2019-03-14 22:54:55.267 27015-27015/com.example.assignment5 D/Lifecycle: In onCreateView
2019-03-14 22:54:55.273 27015-27015/com.example.assignment5 D/Lifecycle: In onActivityCreated
2019-03-14 22:54:55.273 27015-27015/com.example.assignment5 D/Lifecycle: In onStart
2019-03-14 22:54:55.273 27015-27015/com.example.assignment5 D/Lifecycle: In Activity onStart
2019-03-14 22:54:55.277 27015-27015/com.example.assignment5 D/Lifecycle: In Activity onResume
2019-03-14 22:54:55.277 27015-27015/com.example.assignment5 D/Lifecycle: In onResume
2019-03-14 22:55:31.324 27015-27015/com.example.assignment5 D/Lifecycle: In onPause
2019-03-14 22:55:31.324 27015-27015/com.example.assignment5 D/Lifecycle: In Activity onPause
2019-03-14 22:55:32.080 27015-27015/com.example.assignment5 D/Lifecycle: In onStop
2019-03-14 22:55:32.081 27015-27015/com.example.assignment5 D/Lifecycle: In Activity onStop
2019-03-14 22:55:32.085 27015-27015/com.example.assignment5 D/Lifecycle: In onDestroyView
2019-03-14 22:55:32.088 27015-27015/com.example.assignment5 D/Lifecycle: In onDestroy
2019-03-14 22:55:32.089 27015-27015/com.example.assignment5 D/Lifecycle: In onDetach
2019-03-14 22:55:32.089 27015-27015/com.example.assignment5 D/Lifecycle: In Activity onDestroy
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_life_cycle1);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentCycle fragmentCycle=new FragmentCycle();
        fragmentTransaction.add(R.id.fragmentDemo, fragmentCycle);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "In Activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "In Activity onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "In Activity onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "In Activity onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "In Activity onDestroy");

    }
}
