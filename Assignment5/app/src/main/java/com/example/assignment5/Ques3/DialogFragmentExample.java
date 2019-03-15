package com.example.assignment5.Ques3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment5.R;

public class DialogFragmentExample extends DialogFragment {

    EditText nameET, phoneET;
    Button button;

    public DialogFragmentExample() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vieww=inflater.inflate(R.layout.fragment_dialogfragment, null);
        nameET= vieww.findViewById(R.id.nameEditText);
        phoneET= vieww.findViewById(R.id.phoneEditText);
        button= vieww.findViewById(R.id.submitButton);
        return vieww;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Submitted", Toast.LENGTH_LONG);
            }
        });
    }
}
