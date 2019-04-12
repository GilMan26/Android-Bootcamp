package com.example.assignmentweek.Fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Response.Datum;
import com.example.assignmentweek.databinding.FragmentDetailsBinding;

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

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
