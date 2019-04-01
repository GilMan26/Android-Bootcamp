package com.example.assignment10;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment10.databinding.RowItemBinding;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    RowItemBinding binding;
    List<Model> list;
    Context context;

    public CustomAdapter(List<Model> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=(LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,R.layout.row_item, viewGroup, false);
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup,false);
//        View itemView=inflater.inflate(R.layout.item_row, viewGroup);
        return new CustomViewHolder(itemView);
    }

    public void setlist(List<Model> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        final Model model = list.get(i);
        customViewHolder.binding.namePath.setText(model.getName());

        if (model.getType() == Model.TYPE_DIRECTORY) {
            binding.namePath.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_folder_black_24dp, 0, 0, 0);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).populateData(model.getPath());
                }
            });
        } else if (model.getType() == Model.TYPE_FILE) {
            binding.namePath.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_insert_drive_file_black_24dp, 0, 0, 0);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        RowItemBinding binding;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DataBindingUtil.bind(itemView);
        }

        public void bind(Model model){
            binding.setModel(model);
        }
    }
}
