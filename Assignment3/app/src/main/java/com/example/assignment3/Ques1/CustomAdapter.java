package com.example.assignment3.Ques1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    List<RowPojo> list = new ArrayList<>();

    public void addDataToList(List<RowPojo> list){
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public CustomAdapter(List<RowPojo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {
        RowPojo rowElement=list.get(position);

        customViewHolder.name.setText(rowElement.getName());
        customViewHolder.desc.setText(rowElement.getDesc());
        customViewHolder.price.setText(rowElement.getPrice());
        customViewHolder.review.setText(rowElement.getReview());
        customViewHolder.date.setText(rowElement.getDate());
        customViewHolder.ratinigBar.setRating(rowElement.getRating());
        customViewHolder.image.setImageResource(R.drawable.sundae);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView  name, desc, price, review, date;
        RatingBar ratinigBar;
        ImageView image;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);
            date=itemView.findViewById(R.id.date);
            price=itemView.findViewById(R.id.price);
            review=itemView.findViewById(R.id.review);
            ratinigBar=itemView.findViewById(R.id.ratingBar);
            image=itemView.findViewById(R.id.imageView);
        }
    }
}

