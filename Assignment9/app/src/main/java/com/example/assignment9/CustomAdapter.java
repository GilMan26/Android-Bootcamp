package com.example.assignment9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RowViewHolder> {
    List<Post> list=new ArrayList<>();

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_layout, viewGroup,false);
        return new RowViewHolder(itemView);
    }

    public CustomAdapter(List<Post> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder viewHolder, int position) {
        Post data= list.get(position);
        Context context = viewHolder.imageView.getContext();
        if (context != null) {
            Glide.with(context)
                    .load(data.getProfileImage())
                    .into(viewHolder.imageView);
        }

        viewHolder.nameTV.setText(data.getName());
        viewHolder.messageTv.setText(data.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RowViewHolder extends RecyclerView.ViewHolder{
        TextView nameTV, messageTv;
        ImageView imageView;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameTV);
            messageTv=itemView.findViewById(R.id.messageTV);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
