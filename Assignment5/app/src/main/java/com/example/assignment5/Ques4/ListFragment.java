package com.example.assignment5.Ques4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.assignment5.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    List<String> list=new ArrayList<>();
    ListAdapter adapter;
    Communication communication;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return  inflater.inflate(R.layout.list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        populateData();
        adapter=new ListAdapter(list, communication);
        recyclerView.setAdapter(adapter);
    }


    public void populateData() {
        for (int i = 1; i <= 20; i++) {
            list.add("Element : " + i);
        }
    }

    interface Communication {
        void communicate(Bundle bundle);
    }

}
