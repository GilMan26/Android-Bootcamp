package com.example.assignment14;

import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment14.databinding.ItemRowBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RowViewHolder> {
    List<User> list;
    ItemRowBinding binding;

    public CustomAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CustomAdapter.RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false));
    }

    public void setUsers(List<User> users){
        this.list = users;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.RowViewHolder holder, int position) {
        User user=list.get(position);
        binding.setUser(user);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class RowViewHolder extends RecyclerView.ViewHolder{
        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
        public void bind(User user){
            binding.setUser(user);
        }
    }
}
