package com.politecnico.TouristSitesColombia;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class DialogHelper {

    public interface OnFavoriteChangeListener {
        void onChanged();
    }

    public static void mostrarDetalle(Context context, TurismoItem item, boolean esDesdeFavoritos, OnFavoriteChangeListener listener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detalle_imagen);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgFoto = dialog.findViewById(R.id.imgDetalleFoto);
        TextView tvTitulo = dialog.findViewById(R.id.tvDetalleTitulo);
        TextView tvDesc = dialog.findViewById(R.id.tvDetalleDesc);
        LinearLayout btnFavorito = dialog.findViewById(R.id.btnModalFavorito);
        LinearLayout btnDescargar = dialog.findViewById(R.id.btnModalDescargar);
        LinearLayout btnCompartir = dialog.findViewById(R.id.btnModalCompartir);
        ImageView icFavorito = dialog.findViewById(R.id.icModalFavorito);

        tvTitulo.setText(item.getTitulo());
        tvDesc.setText(item.getDescripcion());
        Glide.with(context).load(item.getUrlImagen()).into(imgFoto);

        FavoritesManager favManager = new FavoritesManager(context);

        actualizarIconoFavorito(favManager.isFavorite(item), icFavorito);

        if (!esDesdeFavoritos) {
            btnDescargar.setVisibility(View.GONE);
            btnCompartir.setVisibility(View.GONE);
        }

        btnFavorito.setOnClickListener(v -> {
            boolean isNowFavorite = favManager.toggleFavorite(item);
            actualizarIconoFavorito(isNowFavorite, icFavorito);
            Toast.makeText(context, isNowFavorite ? "Agregado a favoritos" : "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
            if (listener != null) listener.onChanged();
        });

        btnDescargar.setOnClickListener(v -> {
            Toast.makeText(context, "Descargando...", Toast.LENGTH_SHORT).show();
            Glide.with(context).asBitmap().load(item.getUrlImagen()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    MediaUtils.saveImageToGallery(context, resource, item.getTitulo());
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
        });

        btnCompartir.setOnClickListener(v -> {
            Toast.makeText(context, "Preparando imagen...", Toast.LENGTH_SHORT).show();
            Glide.with(context).asBitmap().load(item.getUrlImagen()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    MediaUtils.shareImage(context, resource, "¡Mira este hermoso lugar: " + item.getTitulo() + "!");
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
        });

        dialog.show();
    }

    private static void actualizarIconoFavorito(boolean isFav, ImageView icon) {
        if (isFav) {
            icon.setImageResource(android.R.drawable.btn_star_big_on);
            icon.setColorFilter(Color.parseColor("#F23D5E"));
        } else {
            icon.setImageResource(android.R.drawable.btn_star_big_off);
            icon.setColorFilter(Color.parseColor("#999999"));
        }
    }
}
