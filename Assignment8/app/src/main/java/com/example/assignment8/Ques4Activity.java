package com.example.assignment8;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class Ques4Activity extends AppCompatActivity {
    Constraints constraints;
    TextView textView;
    PeriodicWorkRequest periodicWorkRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques4);
        textView=findViewById(R.id.textView);
        //constraints set to be used with work manager
        constraints=new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true).build();

        //periodicworkrequest calls the same thing every period (5 seconds) in this case
        periodicWorkRequest=new PeriodicWorkRequest.Builder(WorkerClass.class,5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

    }


    public void startWorkManager(View view){
        WorkManager.getInstance().enqueue(periodicWorkRequest);
        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        textView.append(workInfo.getState().name() + "\n");
                    }
                });
    }
}
