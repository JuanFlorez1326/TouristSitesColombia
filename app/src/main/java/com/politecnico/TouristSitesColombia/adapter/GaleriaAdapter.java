package com.politecnico.TouristSitesColombia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.politecnico.TouristSitesColombia.R;
import com.politecnico.TouristSitesColombia.model.TurismoItem;

import java.util.ArrayList;
import java.util.List;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder> {

    private List<TurismoItem> itemsOriginales;
    private List<TurismoItem> itemsFiltrados;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(TurismoItem item);
    }

    public GaleriaAdapter(List<TurismoItem> items, OnItemClickListener listener) {
        this.itemsOriginales = new ArrayList<>(items);
        this.itemsFiltrados = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_galeria, parent, false);
        return new GaleriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {
        TurismoItem item = itemsFiltrados.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getUrlImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgFotoGaleria);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemsFiltrados.size();
    }

    public void filtrar(String texto) {
        itemsFiltrados.clear();
        if (texto.isEmpty()) {
            itemsFiltrados.addAll(itemsOriginales);
        } else {
            texto = texto.toLowerCase();
            for (TurismoItem item : itemsOriginales) {
                if (item.getTitulo().toLowerCase().contains(texto) || item.getDescripcion().toLowerCase().contains(texto)) {
                    itemsFiltrados.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFotoGaleria;

        public GaleriaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoGaleria = itemView.findViewById(R.id.imgFotoGaleria);
        }
    }
}