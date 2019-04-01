package com.example.assignment14;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.assignment14.databinding.ActivityMainBinding;
import com.example.assignment14.databinding.AddDialogBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomAdapter adapter;
    MyViewModel viewModel;
    ActivityMainBinding binding;
    UserDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        database=UserDatabase.getInstance(getApplicationContext());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));



        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // update recyclerview
                //Toast.makeText(MainActivity.this, "OnChanged", Toast.LENGTH_SHORT).show();
                adapter.setUsers(users);
            }
        });

    }

    public void handleButtons(){
        binding.addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.add_dialog);
                final AddDialogBinding dialogBinding=DataBindingUtil.setContentView(MainActivity.this, R.layout.add_dialog);
                builder.setTitle("Add User");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name=dialogBinding.nameET.getText().toString();
                        String email=dialogBinding.emailET.getText().toString();
                        User user=new User(name, email);
                        database.userDao.addUser(user);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.userDao.dropDb();
                    }
                });
                builder.show();
            }
        });

        binding.clearDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Are you s ure you want to delete Database ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }
}
