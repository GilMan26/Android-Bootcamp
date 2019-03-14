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
import java.util.List;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    List<String> list;
    ListAdapter adapter;
    Communication communication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communication = (Communication) context;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        populateData();
        adapter = new ListAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void populateData() {
        for (int i = 1; i <= 20; i++) {
            list.add("Element : " + i);
        }
    }

    interface Communication {
        void communicate(String string);
    }

    public void sendData() {
        communication.communicate("hello");
    }
}
