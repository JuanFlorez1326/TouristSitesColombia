package com.politecnico.TouristSitesColombia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.politecnico.TouristSitesColombia.databinding.FragmentGaleriaBinding;


public class GaleriaFragment extends Fragment {

    private FragmentGaleriaBinding binding;
    private GaleriaAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGaleriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvGaleria.setLayoutManager(staggeredGridLayoutManager);

        adapter = new GaleriaAdapter(DummyData.getGaleria(), item -> {
            DialogHelper.mostrarDetalle(requireContext(), item, false, null);
        });

        binding.rvGaleria.setAdapter(adapter);

        binding.etBuscadorGaleria.addTextChangedListener(new TextWatcher() {
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