package com.example.lb4_town.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lb4_town.R;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_f1 = view.findViewById(R.id.btn_fr1);
        Button btn_f2 = view.findViewById(R.id.btn_fr2);
        NavController controller = Navigation.findNavController(view);

        btn_f1.setOnClickListener(v->controller.navigate(R.id.fragment1));
        btn_f2.setOnClickListener(v->controller.navigate(R.id.fragment2));
    }
}
