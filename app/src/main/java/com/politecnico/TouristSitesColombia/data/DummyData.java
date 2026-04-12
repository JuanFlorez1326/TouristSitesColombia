package com.politecnico.TouristSitesColombia.data;

import com.politecnico.TouristSitesColombia.model.TurismoItem;

import java.util.ArrayList;
import java.util.List;


public class DummyData {

    public static List<TurismoItem> getGaleria() {
        List<TurismoItem> list = new ArrayList<>();

        list.add(new TurismoItem("g1", "Atardecer Tolú", "Hermoso atardecer en el Golfo de Morrosquillo", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g2", "Playa Blanca Barú", "Mar cristalino del Caribe colombiano", "https://images.unsplash.com/photo-1519046904884-53103b34b206?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g3", "Islas de San Bernardo", "Archipiélago paradisíaco", "https://images.unsplash.com/photo-1559494007-9f5847c49d94?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g4", "San Andrés Isla", "El mar de los 7 colores", "https://images.unsplash.com/photo-1500049242364-5f500807cdd7?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g5", "Palmeras Caribeñas", "Brisa marina bajo el sol", "https://images.unsplash.com/photo-1471922694854-ff1b63b20054?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));

        list.add(new TurismoItem("g6", "Parque Tayrona", "Selva tropical junto al mar", "https://images.unsplash.com/photo-1448375240586-882707db888b?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g7", "Cascada en Colombia", "Ríos y cascadas de la región andina", "https://images.unsplash.com/photo-1504893524553-b855bce32c67?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g8", "Valle del Cocora", "Palmas de cera, árbol nacional", "https://images.unsplash.com/photo-1518182170546-07661fd94144?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g9", "Manglares del Caribe", "Ecosistema único del litoral", "https://images.unsplash.com/photo-1516026672322-bc52d61a55d5?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));

        list.add(new TurismoItem("g10", "Cartagena Histórica", "Ciudad amurallada Patrimonio de la Humanidad", "https://images.unsplash.com/photo-1583005543166-a02aa8f44fbe?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g11", "Medellín de Noche", "La ciudad de la eterna primavera", "https://images.unsplash.com/photo-1599059813005-11265ba4b4ce?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g12", "Bogotá Panorámica", "Capital cultural y gastronómica", "https://images.unsplash.com/photo-1564509150-0b1a5e1a9e1e?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g13", "Cabañas de Descanso", "Alojamiento rústico en la naturaleza", "https://images.unsplash.com/photo-1510798831971-661eb04b3739?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));

        list.add(new TurismoItem("g14", "Buceo en el Caribe", "Arrecifes de coral colombianos", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));
        list.add(new TurismoItem("g15", "Senderismo Andino", "Trekking por los Andes colombianos", "https://images.unsplash.com/photo-1551632811-561732d1e306?w=600&q=70&auto=format&fit=crop", null, "GALERIA"));

        return list;
    }
}