package com.example.assignment13;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment13.databinding.ItemRowBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RowViewHolder> {
    List<Post> list=new ArrayList<>();
    ItemRowBinding binding;

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=(LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,R.layout.item_row, viewGroup, false);
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup,false);
//        View itemView=inflater.inflate(R.layout.item_row, viewGroup);
        return new RowViewHolder(itemView);
    }

    public CustomAdapter(List<Post> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder viewHolder, int position) {
        Post data= list.get(position);

        Post post=new Post( data.getMessage(),data.getName());
        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RowViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ItemRowBinding binding;


        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }

        public void bind(Post post) {
            binding.setPost(post);
//            Context context = imageView.getContext();
//            if (context != null) {
//                Glide.with(context)
//                        .load(post.getProfileImage())
//                        .into(imageView);
//            }
        }

    }
}
