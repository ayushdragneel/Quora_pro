package com.example.quora_pro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class questionlist extends Fragment {
        RecyclerView recyclerView;
        RecyclerViewAdapter recyclerViewAdapter;
        ArrayList<String> question;
        ArrayList<String> answer;
    public questionlist(ArrayList<String> question,ArrayList<String> answer) {
        // Required empty public constructor
        this.question=question;
        this.answer=answer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v=inflater.inflate(R.layout.fragment_questionlist, container, false);
        recyclerView=v.findViewById(R.id.recyclerViewid);
         recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewAdapter=new RecyclerViewAdapter(getContext(),question,answer);
        recyclerView.setAdapter(recyclerViewAdapter);


        return  v;
    }
}