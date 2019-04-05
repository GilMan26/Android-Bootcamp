package com.example.assignmentweek.Fragments;

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

import com.example.assignmentweek.CustomAdapter;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Response.Datum;

import java.util.List;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    List<Datum> list;
    CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.list_fragment, container);
         recyclerView=view.findViewById(R.id.recyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
         recyclerView.setAdapter(adapter);
         return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter=new CustomAdapter(list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
    }
}
