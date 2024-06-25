package com.AdrianTech.Modelos;

public class Producto implements Comparable<Producto>{
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    @Override
    public int compareTo(Producto producto) {
        return this.getNombre().compareTo(producto.getNombre());
    }
}
