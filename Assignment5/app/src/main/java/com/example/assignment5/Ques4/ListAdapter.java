package com.example.assignment5.Ques4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment5.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private List<String> list;

    public ListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_row, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        String data=list.get(position);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void sendData(int data){

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.fragmentRow);
        }
    }
}
