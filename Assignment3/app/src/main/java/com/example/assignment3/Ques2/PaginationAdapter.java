package com.example.assignment3.Ques2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.PaginationViewHolder> {
    List<String> list;

    public PaginationAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PaginationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pagin_row, viewGroup,false);
        return new PaginationViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PaginationViewHolder paginationViewHolder, int position) {
        String str=list.get(position);
        paginationViewHolder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PaginationViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public PaginationViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
