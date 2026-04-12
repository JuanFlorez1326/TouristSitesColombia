package com.politecnico.TouristSitesColombia;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.politecnico.TouristSitesColombia.databinding.FragmentVideosBinding;

import java.util.ArrayList;
import java.util.List;


public class VideosFragment extends Fragment {

    private FragmentVideosBinding binding;
    private VideoAdapter adapter;
    private List<TurismoItem> misVideosLocales;

    private final ActivityResultLauncher<String> selectorDeVideo = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    agregarVideoALaLista(uri);
                }
            });

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVideosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        misVideosLocales = new ArrayList<>();

        binding.rvVideosLocales.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoAdapter(misVideosLocales);
        binding.rvVideosLocales.setAdapter(adapter);

        binding.btnAgregarVideo.setOnClickListener(v -> {
            selectorDeVideo.launch("video/*");
        });

        return root;
    }

    private void agregarVideoALaLista(Uri uri) {
        String idGenerado = "local_" + System.currentTimeMillis();
        TurismoItem nuevoVideo = new TurismoItem(
                idGenerado,
                "Video de Galería",
                "Video local cargado desde tu dispositivo",
                "",
                uri.toString(),
                "VIDEO_LOCAL"
        );

        misVideosLocales.add(nuevoVideo);

        adapter.notifyItemInserted(misVideosLocales.size() - 1);

        binding.rvVideosLocales.smoothScrollToPosition(misVideosLocales.size() - 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}