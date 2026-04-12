package com.politecnico.TouristSitesColombia.model;

import java.io.Serializable;
import java.util.Objects;

public class TurismoItem implements Serializable {

    private String id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String urlVideoYoutube;
    private String tipo; // "REGION", "DESTINO", "GALERIA", "VIDEO"

    public TurismoItem(String id, String titulo, String descripcion, String urlImagen, String urlVideoYoutube, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.urlVideoYoutube = urlVideoYoutube;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getUrlVideoYoutube() {
        return urlVideoYoutube;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurismoItem that = (TurismoItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}