package com.politecnico.TouristSitesColombia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.politecnico.TouristSitesColombia.databinding.FragmentRegionesBinding;

public class RegionesFragment extends Fragment {

    private FragmentRegionesBinding binding;
    private TurismoAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvRegiones.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TurismoAdapter(DummyData.getRegiones(), item -> {
            Toast.makeText(getContext(), "Seleccionado: " + item.getTitulo(), Toast.LENGTH_SHORT).show();
        });

        binding.rvRegiones.setAdapter(adapter);

        binding.etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}