package com.example.assignment10;

import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.assignment10.databinding.ActivityMainBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CustomAdapter adapter;
    String rootPath, parentPath;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new CustomAdapter(list, MainActivity.this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        rootPath = Environment.getExternalStorageDirectory().toString();

        parentPath = Environment.getExternalStorageDirectory().toString();
        populateData(rootPath);

    }

//    public void populate(){
//        for(int i=0;i<30;i++){
//            String name="Name "+i;
//            String message="Message "+i;
//            int id=i;
//            Model model=new Model(name, message, id);
//            list.add(model);
//        }
//    }

    public void populateData(String selectedPath) {
        File currentDir = new File(selectedPath);
        parentPath = selectedPath;
//        list = Arrays.asList(currentDir.listFiles());
        if (currentDir.listFiles() != null) {
            list.clear();
            for (File file : currentDir.listFiles()) {
                if (file.isDirectory())
                    list.add(new Model(file.getName(), file.getPath(), Model.TYPE_DIRECTORY));
                else
                    list.add(new Model(file.getName(), file.getPath(), Model.TYPE_FILE));
            }

            adapter.setlist(list);

            adapter.notifyDataSetChanged();
        }
    }
}
