package com.politecnico.TouristSitesColombia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TurismoAdapter extends RecyclerView.Adapter<TurismoAdapter.TurismoViewHolder> {

    private List<TurismoItem> itemsOriginales;
    private List<TurismoItem> itemsFiltrados;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(TurismoItem item);
    }

    public TurismoAdapter(List<TurismoItem> items, OnItemClickListener listener) {
        this.itemsOriginales = new ArrayList<>(items);
        this.itemsFiltrados = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TurismoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turismo, parent, false);
        return new TurismoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurismoViewHolder holder, int position) {
        TurismoItem item = itemsFiltrados.get(position);
        holder.tvTitulo.setText(item.getTitulo());

        // Usamos Glide para cargar la imagen desde la URL de forma optimizada
        Glide.with(holder.itemView.getContext())
                .load(item.getUrlImagen())
                .into(holder.imgFoto);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemsFiltrados.size();
    }

    // Lógica para el buscador
    public void filtrar(String texto) {
        itemsFiltrados.clear();
        if (texto.isEmpty()) {
            itemsFiltrados.addAll(itemsOriginales);
        } else {
            texto = texto.toLowerCase();
            for (TurismoItem item : itemsOriginales) {
                if (item.getTitulo().toLowerCase().contains(texto)) {
                    itemsFiltrados.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class TurismoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvTitulo;

        public TurismoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
        }
    }
}