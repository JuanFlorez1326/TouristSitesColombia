package com.politecnico.TouristSitesColombia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.politecnico.TouristSitesColombia.adapter.GaleriaAdapter;
import com.politecnico.TouristSitesColombia.databinding.FragmentPerfilBinding;
import com.politecnico.TouristSitesColombia.model.TurismoItem;
import com.politecnico.TouristSitesColombia.utils.DialogHelper;
import com.politecnico.TouristSitesColombia.utils.FavoritesManager;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private FavoritesManager favManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favManager = new FavoritesManager(requireContext());
        cargarTopFavoritos();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarTopFavoritos();
    }

    private void cargarTopFavoritos() {
        List<TurismoItem> todosLosFavoritos = favManager.getFavorites();
        List<TurismoItem> top3Favoritos = new ArrayList<>();

        int limite = Math.min(todosLosFavoritos.size(), 3);
        for (int i = 0; i < limite; i++) {
            top3Favoritos.add(todosLosFavoritos.get(i));
        }

        if (top3Favoritos.isEmpty()) {
            binding.rvTopFavorites.setVisibility(View.GONE);
            binding.tvEmptyTopFavs.setVisibility(View.VISIBLE);
        } else {
            binding.rvTopFavorites.setVisibility(View.VISIBLE);
            binding.tvEmptyTopFavs.setVisibility(View.GONE);

            binding.rvTopFavorites.setLayoutManager(new GridLayoutManager(getContext(), 3));

            GaleriaAdapter adapter = new GaleriaAdapter(top3Favoritos, item -> {
                DialogHelper.mostrarDetalle(requireContext(), item, true, () -> {
                    cargarTopFavoritos();
                });
            });

            binding.rvTopFavorites.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}