package com.example.assignment6.Ques1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment6.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDBAdapter extends RecyclerView.Adapter<RecyclerDBAdapter.CustomViewHolder> {
    List<Employee> list=new ArrayList<>();
    Context context;
    MyDBHandler myDBHandler;


    public RecyclerDBAdapter(Context context, List<Employee> list) {
        this.context=context;
        this.list = list;
        Log.d("size", this.list.size()+"");
        myDBHandler=new MyDBHandler(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup,false);
        CustomViewHolder customViewHolder=new CustomViewHolder(itemView);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder,  final int position) {
        final Employee data=list.get(position);
        customViewHolder.nameTextView.setText(data.getName());
        customViewHolder.addressTextView.setText(data.getAddress());
        customViewHolder.phoneTextView.setText(data.getPhone());
        customViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nameEditText, addressEditText, phoneEditText;
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.dialog_layout, null);
                nameEditText=dialogView.findViewById(R.id.nameEditText);
                addressEditText=dialogView.findViewById(R.id.addressEditText);
                phoneEditText=dialogView.findViewById(R.id.phoneEditText);
                nameEditText.setHint(data.getName());
                phoneEditText.setHint(data.getPhone());
                addressEditText.setHint(data.getAddress());
                builder.setView(dialogView);
                builder.setTitle("Edit Employee");
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Employee newEmployee=new Employee();
                        newEmployee.setName(nameEditText.getText().toString());
                        newEmployee.setAddress(addressEditText.getText().toString());
                        newEmployee.setPhone(phoneEditText.getText().toString());
                        myDBHandler.editEmployee(data.getId(), newEmployee);
                        notifyDataSetChanged();
                        ((Activity)context).finish();
                        context.startActivity(((Activity)context).getIntent());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        customViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you sure you want to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDBHandler.deleteEmployee(data.getId());
                        list.remove(data);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, addressTextView, phoneTextView;
        Button editButton, deleteButton;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.nameTextView);
            addressTextView=itemView.findViewById(R.id.addressTextView);
            phoneTextView=itemView.findViewById(R.id.phoneTextView);
            editButton=itemView.findViewById(R.id.editButton);
            deleteButton=itemView.findViewById(R.id.deleteButton);
        }
    }
}
