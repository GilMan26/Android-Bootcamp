package com.example.assignmentweek.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmentweek.Interfaces.IAddUser;
import com.example.assignmentweek.Interfaces.IUpdateUser;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Request.UserRequest;
import com.example.assignmentweek.databinding.AddDialogFragmentBinding;

public class UpdateDialog extends DialogFragment {
    AddDialogFragmentBinding binding;
    IUpdateUser iUpdateUser;
    long id;

    public void setInstance(IUpdateUser instance){
        iUpdateUser=instance;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Update User");
        builder.setView(R.layout.update_dialog_fragment);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserRequest request=new UserRequest(binding.jobET.getText().toString(), binding.nameET.getText().toString());
                iUpdateUser.updateUser(id, request);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getArguments().getLong("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_dialog_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }
}
