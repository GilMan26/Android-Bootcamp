package com.example.assignment6.Ques1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment6.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Employee> list=new ArrayList<>();
    RecyclerDBAdapter adapter;
    MyDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.mainRecycler);
        myDBHandler=new MyDBHandler(this);
        defineAdapter();
        fetchData();
        adapter=new RecyclerDBAdapter(this, list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee(v);
            }
        });

    }

    public void defineAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerDBAdapter(this, list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void fetchData(){
        Cursor cursor = myDBHandler.fetch();
        if (cursor.getCount() != 0) {
            while (!cursor.isLast()) {
                list.add(new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                cursor.moveToNext();
            }
            list.add(new Employee(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        adapter.notifyDataSetChanged();
        cursor.close();
    }

    public void editEmployee(View vieww){

    }

    public void addEmployee(View view){
        final EditText nameET, addressET, phoneET;

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View dialogView=getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);
        nameET=dialogView.findViewById(R.id.nameEditText);
        addressET=dialogView.findViewById(R.id.addressEditText);
        phoneET=dialogView.findViewById(R.id.phoneEditText);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name=nameET.getText().toString();
                String address=addressET.getText().toString();
                String phone=phoneET.getText().toString();
                Employee employee=new Employee();
                employee.setName(name);
                employee.setAddress(address);
                employee.setPhone(phone);
                list.add(employee);
                myDBHandler.addEmployee(employee);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("Add Employee");
        builder.create().show();

    }

//    public void addEmployeeFragment(View view){
//        MyDialogFragment myDialogFragment = new MyDialogFragment();
//        myDialogFragment.show(getSupportFragmentManager(), "add fragment");
//        final EditText nameET, addressET, phoneET;
//        Button btn;
//        nameET=findViewById(R.id.nameEditText);
//        addressET=findViewById(R.id.addressEditText);
//        phoneET=findViewById(R.id.phoneEditText);
//        btn=findViewById(R.id.addButton);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=nameET.getText().toString();
//                String address=addressET.getText().toString();
//                String phone=phoneET.getText().toString();
//                Employee employee=new Employee();
//                employee.setName(name);
//                employee.setAddress(address);
//                employee.setPhone(phone);
//                list.add(employee);
//                myDBHandler.addEmployee(employee);
//            }
//        });
//    }

}
