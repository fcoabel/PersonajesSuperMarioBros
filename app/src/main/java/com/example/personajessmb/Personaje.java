package com.example.personajessmb;

import java.io.Serializable;

// clase personaje, que es el modelo usado para los datos
public class Personaje implements Serializable {
    private String img, nombre, descripcion, habilidad;

    public Personaje(String img, String nombre, String descripcion, String habilidad) {
        this.img = img;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidad = habilidad;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "img='" + img + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", habilidad='" + habilidad + '\'' +
                '}';
    }
}
