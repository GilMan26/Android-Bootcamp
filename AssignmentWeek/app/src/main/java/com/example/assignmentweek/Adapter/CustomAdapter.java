package com.example.assignmentweek.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignmentweek.Interfaces.IUserTouchListener;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Response.Datum;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RowViewHolder> {

    List<Datum> list;
    IUserTouchListener listener;

    public CustomAdapter(List<Datum> list) {
        this.list = list;
    }

    public void setTouchListener(IUserTouchListener instance){
        listener=instance;
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
        final Context context = rowViewHolder.imageView.getContext();
        if (context != null) {
            Glide.with(context)
                    .load(data.getAvatar())
                    .into(rowViewHolder.imageView);
        }
        rowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getClickedUser(data);
            }
        });
        rowViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Are you sure you want to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.deleteUser(data.getId());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
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
