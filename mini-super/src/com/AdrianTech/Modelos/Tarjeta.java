package com.AdrianTech.Modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarjeta {

    private double credito = 0;
    private double limite;



    public Tarjeta( double credito, double limite){

        this.credito = credito;
        this.limite = limite;

    }


    public double getCredito() {
        return credito;
    }

    public double getLimite() {
           return limite;
    }


    public void setLimite(double limite) {

      validarLimite(limite);
    }
    public void validarLimite(double limite){
        Scanner scanner = new Scanner(System.in);

            if(limite <= this.credito && limite > 0){
                this.limite = limite;
                System.out.println("Límite validado y correcto!!\n");

            }else {
                System.out.println("El límite ingresado no es valido, ya que su saldo es menor, por favor, ingresa otro:)");
                System.out.print("Límite : $");
                setLimite(scanner.nextDouble());
            }



    }

    public void disminuirCredito(Producto producto){
        if (this.credito > 0) {
            this.credito = this.credito - producto.getPrecio();
        }else {
            System.out.println("No tienes saldo en tu tarjeta");
        }
    }
    public void disminuirLimite(Producto producto){
        if (this.limite > 0) {
            this.limite = limite - producto.getPrecio() ;
        }else {
            System.out.println("Haz gastado todo el credito de tu límite");
        }
    }

    public void pagar(Tarjeta tarjeta, ArrayList<Producto> productos, Compra compra) {

        if (tarjeta.getCredito() > 0) {

            for (Producto producto : productos) {

                    if (producto.getPrecio() <= tarjeta.getCredito() && producto.getPrecio() <= tarjeta.getLimite()) {
                        pagosAceptados(producto, tarjeta, compra);

                    }else {
                        pagosNoAceptados(producto,tarjeta, compra);

                    }

                }


        } else
            System.out.println("Tu tarjeta tiene $0, intenta otro metodo de pago");

    }

    public void pagosAceptados(Producto producto ,Tarjeta tarjeta, Compra compra){

        compra.productoComprado(producto);
        tarjeta.disminuirCredito(producto);
        tarjeta.disminuirLimite(producto);
        compra.setCosasCompradas(compra.getCosasCompradas() + 1);
        compra.setTotalDeCuentaDeCompra(compra.getTotalDeCuentaDeCompra() + producto.getPrecio());


    }
    public void pagosNoAceptados(Producto producto, Tarjeta tarjeta, Compra compra){
        if (producto.getPrecio() > tarjeta.getCredito() && producto.getPrecio() > tarjeta.getLimite()){
            System.out.println("Tu saldo y tu limite de compra no cubre para comprar este producto '" + producto.getNombre() + "' - $" + producto.getPrecio());
            if (compra.getCosasCompradas() <= 0) {

                compra.setCosasCompradas(0);
                compra.setTotalDeCuentaDeCompra(0);

            }
        } else if (producto.getPrecio() > tarjeta.getCredito()){
            System.out.println("Tu saldo es insuficiente para comprar este producto '" + producto.getNombre() + "' - $" + producto.getPrecio());
            if (compra.getCosasCompradas() <= 0) {

                compra.setCosasCompradas(0);
                compra.setTotalDeCuentaDeCompra(0);

            }
        } else if (producto.getPrecio() > tarjeta.getLimite()) {
            System.out.println("Tu límite de tarjeta no cubre la compra de este producto '" + producto.getNombre() + "' - $" + producto.getPrecio());
            if (compra.getCosasCompradas() <= 0) {

                compra.setCosasCompradas(0);
                compra.setTotalDeCuentaDeCompra(0);

            }
        }

    }



}
