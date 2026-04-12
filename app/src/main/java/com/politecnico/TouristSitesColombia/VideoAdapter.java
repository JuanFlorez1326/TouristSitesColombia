package com.politecnico.TouristSitesColombia;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<TurismoItem> listaVideos;

    public VideoAdapter(List<TurismoItem> items) {
        this.listaVideos = items;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        TurismoItem item = listaVideos.get(position);
        holder.tvVideoTitle.setText(item.getTitulo());
        holder.tvVideoDesc.setText(item.getDescripcion());

        try {
            Uri videoUri = Uri.parse(item.getUrlVideoYoutube());

            holder.videoViewLocal.setVideoURI(videoUri);

            MediaController mediaController = new MediaController(holder.itemView.getContext());
            mediaController.setAnchorView(holder.videoViewLocal);
            holder.videoViewLocal.setMediaController(mediaController);

            holder.videoViewLocal.setOnClickListener(v -> {
                if (mediaController.isShowing()) {
                    mediaController.hide();
                } else {
                    mediaController.show(3000);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listaVideos.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoViewLocal;
        TextView tvVideoTitle, tvVideoDesc;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoViewLocal = itemView.findViewById(R.id.videoViewLocal);
            tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);
            tvVideoDesc = itemView.findViewById(R.id.tvVideoDesc);
        }
    }
}