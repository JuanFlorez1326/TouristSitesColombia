package com.politecnico.TouristSitesColombia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.politecnico.TouristSitesColombia.databinding.FragmentFavoritosBinding;

import java.util.List;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritosBinding binding;
    private FavoritesManager favManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favManager = new FavoritesManager(requireContext());

        binding.rvFavoritos.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        cargarFavoritos();

        return root;
    }

    private void cargarFavoritos() {
        List<TurismoItem> listaFavoritos = favManager.getFavorites();

        if (listaFavoritos.isEmpty()) {
            binding.rvFavoritos.setVisibility(View.GONE);
            binding.llEmptyState.setVisibility(View.VISIBLE);
        } else {
            binding.rvFavoritos.setVisibility(View.VISIBLE);
            binding.llEmptyState.setVisibility(View.GONE);

            GaleriaAdapter adapter = new GaleriaAdapter(listaFavoritos, item -> {
                DialogHelper.mostrarDetalle(requireContext(), item, true, () -> {
                    cargarFavoritos();
                });
            });
            binding.rvFavoritos.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
