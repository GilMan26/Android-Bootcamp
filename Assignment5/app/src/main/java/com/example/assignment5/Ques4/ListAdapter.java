package com.example.assignment5.Ques4;

import android.os.Bundle;
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
    ListFragment.Communication communication;

    public ListAdapter(List<String> list, ListFragment.Communication communication) {
        this.list = list;
        this.communication=communication;
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
        myViewHolder.textView.setText(data);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("position", position);
                communication.communicate(bundle);
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

    interface Communicator{
        public void communicate();
    }
}
