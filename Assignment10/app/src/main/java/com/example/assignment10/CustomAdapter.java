package com.example.assignment10;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.assignment10.databinding.RowItemBinding;

import java.io.File;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    RowItemBinding binding;
    List<Model> list;
    Context context;

    public CustomAdapter(List<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.row_item, viewGroup, false);
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
//        View itemView=inflater.inflate(R.layout.item_row, viewGroup);
        return new CustomViewHolder(itemView);
    }

    public void setlist(List<Model> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public String getMimeType(String url) {
        String mimeType = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return mimeType;
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
                    File file = new File(model.getPath());

                    Intent newIntent = new Intent(Intent.ACTION_VIEW);
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    String mimeType = getMimeType(model.getPath());
                    Uri apkURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                    newIntent.setDataAndType(apkURI, mimeType);
                    newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    try {
                        context.startActivity(newIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No handler for this type of file.", Toast.LENGTH_LONG).show();
                    }
//                    File file = new File(model.getPath());
//                    String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
//                            MimeTypeMap.getFileExtensionFromUrl(model.getPath())
//                    );
//
//                    //using content provider to open file
//                    Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
//                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//                    intent.putExtra("PATH", model.getPath());
//                    intent.putExtra("MIMETYPE", mimeType);
//                    intent.setType(mimeType);
//                    intent.setDataAndType(uri, mimeType);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(Intent.createChooser(intent, "Open using"));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        RowItemBinding binding;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(Model model) {
            binding.setModel(model);
        }
    }
}
