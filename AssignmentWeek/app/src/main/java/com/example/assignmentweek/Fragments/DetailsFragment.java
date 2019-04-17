package com.example.assignmentweek.Fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Response.Datum;
import com.example.assignmentweek.databinding.FragmentDetailsBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DetailsFragment extends Fragment {
    Datum data;
    FragmentDetailsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        View view = binding.getRoot();
        data=new Datum(getArguments().getString("avatar"),getArguments().getString("fname"),getArguments().getLong("id"),getArguments().getString("lname"));
        return view;
    }

    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.first.setText(data.getFirstName());
        binding.last.setText(data.getLastName());
        Context context = binding.imageView.getContext();
        if (context != null) {
            Glide.with(context)
                    .load(data.getAvatar())
                    .into(binding.imageView);
        }
        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUserRequest(getContext(), data);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void shareUserRequest(Context context, Datum datum) {
        File imageFile = getOutputMediaFile(context, datum);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,
                context.getPackageName() + ".provider", imageFile));
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Send via"));
    }

    private File getOutputMediaFile(final Context context, Datum usersModel) {
        File mediaStorageDir1 = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        if (!mediaStorageDir1.exists()) {
            if (!mediaStorageDir1.mkdirs()) {
                return null;
            }
        }
        final File imageFile = new File(mediaStorageDir1.getPath() + File.separator +
                "Img_" + usersModel.getId() + "_" +
                usersModel.getFirstName() + "_" + usersModel.getLastName() + ".jpg");

        Glide.with(context).asFile().
                load(usersModel.getAvatar())
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        try {
                            FileUtils.copyFile(resource, imageFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
        return imageFile;
    }
}
