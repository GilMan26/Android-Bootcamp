package com.example.assignment5.Ques4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment5.R;

public class OutputFragment extends Fragment implements ListFragment.Communication {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, container, false);
        textView=view.findViewById(R.id.textView);
        return view;
    }


    public void getData(String string){
        textView.setText(string);
    }


    @Override
    public void communicate(String string) {
        getData(string);
    }
}
