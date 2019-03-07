package com.example.assignment3.Ques3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3.Ques2.PaginationAdapter;
import com.example.assignment3.R;

import java.util.List;

public class MultipleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Model> dataList;
    private final static int IMAGE_VIEW = 1;
    private final static int TEXT_VIEW = 0;
    private final static int TEXT_IMAGE_VIEW = 2;

    public MultipleViewAdapter(List<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == IMAGE_VIEW) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_row, viewGroup, false);
            return new MultipleViewAdapter.ImageViewHolder(itemView);
        } else if (type == TEXT_VIEW) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pagin_row, viewGroup, false);
            return new MultipleViewAdapter.TextViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_text_row, viewGroup, false);
            return new MultipleViewAdapter.ImageTextViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof TextViewHolder) {
            Model model = dataList.get(position);
            ((TextViewHolder) viewHolder).textView1.setText(model.getText());
        }
        if (viewHolder instanceof ImageViewHolder) {
            Model model = dataList.get(position);
            Log.d("image", model.getImage() + "");
            ((ImageViewHolder) viewHolder).imageView1.setImageResource(model.getImage());
        }
        if (viewHolder instanceof ImageTextViewHolder) {
            Model model = dataList.get(position);
            ((ImageTextViewHolder) viewHolder).textView2.setText(model.getText());
            ((ImageTextViewHolder) viewHolder).imageView2.setImageResource(model.getImage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView);
        }
    }

    class ImageTextViewHolder extends RecyclerView.ViewHolder {
        TextView textView2;
        ImageView imageView2;

        public ImageTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView2 = itemView.findViewById(R.id.textTextView);
            imageView2 = itemView.findViewById(R.id.textImageView);
        }
    }
}
