package com.Adrian.modelos;

import java.util.Objects;

public class Personaje {
    String nombre;
    String skinColor;
    String genero;

    public Personaje(DatosApi datos){


        this.nombre = datos.name();
        this.skinColor = datos.skin_color();
        this.genero = datos.gender();



    }

    @Override
    public String toString() {
        return "-Nombre: " + this.nombre + "\n" + "-Genero: " + this.genero + "\n" + "-Skin Color: " + this.skinColor + "\n" ;
    }
}
