package com.example.assignment14;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assignment14.databinding.AddDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomAdapter adapter;
    RecyclerView recyclerView;
    Button addbtn, clearbtn;
    MyViewModel viewModel;
    //    ActivityMainBinding binding;
    UserDatabase database;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyViewModel.MyViewModelFactory factory = new MyViewModel.MyViewModelFactory(this.getApplication());
//        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
                adapter.notifyDataSetChanged();
            }
        });
        list = new ArrayList<>();
        adapter = new CustomAdapter(list);
        recyclerView = findViewById(R.id.recyclerView);
        addbtn = findViewById(R.id.addUser);
        clearbtn = findViewById(R.id.clearDb);
        database = UserDatabase.getInstance(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        handleButtons();

    }

    public void handleButtons() {
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insert(new User("Mandeep", "gillmandeep74@gmail.com"));
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setView(R.layout.add_dialog);
//                final AddDialogBinding dialogBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.add_dialog);
//                builder.setTitle("Add User");
//                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String name = dialogBinding.nameET.getText().toString();
//                        String email = dialogBinding.emailET.getText().toString();
//                        User user = new User(name, email);
//                        viewModel.insert(user);
//                        database.userDao().addUser(user);
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                builder.show();
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteAllUser();
                adapter.notifyDataSetChanged();
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Are you s ure you want to delete Database ?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
            }
        });
    }
}
