package com.politecnico.TouristSitesColombia.data;

import com.politecnico.TouristSitesColombia.model.TurismoItem;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<TurismoItem> getRegiones() {
        List<TurismoItem> list = new ArrayList<>();
        list.add(new TurismoItem("r1", "Costa Caribe", "Descubre playas paradisiacas y ciudades coloniales.", "https://images.unsplash.com/photo-1557456170-0cf4f4d0d362?auto=format&fit=crop&w=600", null, "REGION"));
        list.add(new TurismoItem("r2", "Región Andina", "Montañas majestuosas y clima templado.", "https://images.unsplash.com/photo-1590459528659-195971ce971c?auto=format&fit=crop&w=600", null, "REGION"));
        list.add(new TurismoItem("r3", "Región Pacífica", "Biodiversidad única y avistamiento de ballenas.", "https://images.unsplash.com/photo-1629813354316-291db72b64a2?auto=format&fit=crop&w=600", null, "REGION"));
        return list;
    }

    public static List<TurismoItem> getDestinos() {
        List<TurismoItem> list = new ArrayList<>();
        list.add(new TurismoItem("d1", "Cartagena", "La Ciudad Heroica amurallada.", "https://images.unsplash.com/photo-1583345226999-523c14af084e?auto=format&fit=crop&w=600", null, "DESTINO"));
        list.add(new TurismoItem("d2", "San Andrés", "El mar de los siete colores.", "https://images.unsplash.com/photo-1584286595398-a59f21d313f5?auto=format&fit=crop&w=600", null, "DESTINO"));
        list.add(new TurismoItem("d3", "Tolú y Coveñas", "Playas tranquilas ideales para descansar.", "https://images.unsplash.com/photo-1596395819057-e37f55a8516b?auto=format&fit=crop&w=600", null, "DESTINO"));
        return list;
    }

    public static List<TurismoItem> getVideos() {
        List<TurismoItem> list = new ArrayList<>();
        list.add(new TurismoItem("v1", "Guía Turística: Cartagena", "Recorrido por la ciudad", "https://img.youtube.com/vi/eQ2GgQ3_a-0/hqdefault.jpg", "eQ2GgQ3_a-0", "VIDEO"));
        list.add(new TurismoItem("v2", "Maravillas de San Andrés", "Vista aérea del mar", "https://img.youtube.com/vi/dQw4w9WgXcQ/hqdefault.jpg", "dQw4w9WgXcQ", "VIDEO"));
        return list;
    }

    public static List<TurismoItem> getGaleria() {
        List<TurismoItem> list = new ArrayList<>();
        list.add(new TurismoItem("g1", "Atardecer Tolú", "Hermoso atardecer", "https://images.unsplash.com/photo-1596395819057-e37f55a8516b?auto=format&fit=crop&w=600", null, "GALERIA"));
        list.add(new TurismoItem("g2", "Playa Blanca", "Mar cristalino", "https://images.unsplash.com/photo-1583345226999-523c14af084e?auto=format&fit=crop&w=400&h=600", null, "GALERIA"));
        list.add(new TurismoItem("g3", "Cabañas", "Descanso", "https://images.unsplash.com/photo-1557456170-0cf4f4d0d362?auto=format&fit=crop&w=400&h=300", null, "GALERIA"));
        list.add(new TurismoItem("g4", "Islas de San Bernardo", "Paraíso", "https://images.unsplash.com/photo-1584286595398-a59f21d313f5?auto=format&fit=crop&w=400&h=500", null, "GALERIA"));
        list.add(new TurismoItem("g5", "Palmeras", "Brisa marina", "https://images.unsplash.com/photo-1590459528659-195971ce971c?auto=format&fit=crop&w=400", null, "GALERIA"));
        return list;
    }
}