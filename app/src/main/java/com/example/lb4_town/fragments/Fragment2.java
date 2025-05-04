package com.example.lb4_town.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lb4_town.R;
import com.example.lb4_town.Place;
import com.example.lb4_town.ViewModel;
import com.example.lb4_town.adapters.PlaceAdapter;


import java.util.List;

public class Fragment2 extends Fragment {
    private ViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPlaces);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getPlaces().observe(getViewLifecycleOwner(), places -> {
            recyclerView.setAdapter(new PlaceAdapter(places));
        });

        return view;
    }
}
