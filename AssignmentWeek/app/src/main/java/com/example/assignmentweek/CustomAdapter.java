package com.example.assignmentweek;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignmentweek.Response.Datum;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RowViewHolder> {

    List<Datum> list;


    public CustomAdapter(List<Datum> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup,false);
        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder rowViewHolder, int i) {
        final Datum data=list.get(i);
        rowViewHolder.firstTV.setText(data.getFirstName());
        rowViewHolder.lastTv.setText(data.getLastName());
        Context context = rowViewHolder.imageView.getContext();
        if (context != null) {
            Glide.with(context)
                    .load(data.getAvatar())
                    .into(rowViewHolder.imageView);
        }
        rowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("fname", data.getFirstName());
                bundle.putString("lname",data.getLastName());
                bundle.putString("avatar", data.getAvatar());
                bundle.putLong("id", data.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RowViewHolder extends RecyclerView.ViewHolder{
        TextView idTV, firstTV, lastTv;
        ImageView imageView;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            firstTV=itemView.findViewById(R.id.first);
            lastTv=itemView.findViewById(R.id.last);
        }
    }
}
