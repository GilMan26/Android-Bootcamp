package com.example.assignment14;

import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_row, parent, false);
        return new RowViewHolder(binding);
    }

    public void setUsers(List<User> users){
        this.list = users;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.RowViewHolder holder, int position) {
        User user=list.get(position);
        binding.nameTV.setText(user.getName());
        binding.emailTV.setText(user.getEmail());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class RowViewHolder extends RecyclerView.ViewHolder{
        public RowViewHolder(ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
        }
    }
}
