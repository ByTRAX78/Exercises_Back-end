package com.AdrianTech.Modelos;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Compra {
    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<String> productosComprados = new ArrayList<>();
    String repetir = "no";
    Scanner scanner = new Scanner(System.in);


    private int numeroDeCompra = 0;
    private int cosasCompradas = 0;
    private double totalDeCuentaDeCompra = 0;


    public Compra(){
        numeroDeCompra++;
    }
    public int getNumeroDeCompra() {
        return numeroDeCompra;
    }

    public int getCosasCompradas() {
        return cosasCompradas;
    }

    public void setCosasCompradas(int cosasCompradas) {
        this.cosasCompradas = cosasCompradas;
    }

    public double getTotalDeCuentaDeCompra() {
        return totalDeCuentaDeCompra;
    }

    public void setTotalDeCuentaDeCompra(double totalDeCuentaDeCompra) {
        this.totalDeCuentaDeCompra = totalDeCuentaDeCompra;
    }
    public void productoComprado(Producto producto){

       productosComprados.add(producto.getNombre());



    }
    public Map<String, Integer> encontrarProdcutosRepetidos(List<String> productos) {
        Map<String, Integer> cuentaPalabras = new HashMap<>();

        // Recorrer el array de palabras y contar las ocurrencias
        for (String palabra : productos) {
            if (cuentaPalabras.containsKey(palabra)) {
                cuentaPalabras.put(palabra, cuentaPalabras.get(palabra) + 1);
            } else {
                cuentaPalabras.put(palabra, 1);
            }
        }

        return cuentaPalabras;

    }

    public void mostrarProductosComprados(){

        if (repetir.toLowerCase().equals("no")){
            // Llamar al método para encontrar palabras repetidas

            Map<String, Integer> palabrasRepetidas = encontrarProdcutosRepetidos(productosComprados);

            // Imprimir palabras repetidas
            for (Map.Entry<String, Integer> entrada : palabrasRepetidas.entrySet()) {

                System.out.println(entrada.getKey() + " x" + entrada.getValue() + ".............$" + precioProducto(entrada.getKey()));
            }

        }

    }
    public double precioProducto(String nombre){
        for (Producto producto: productos) {
            if (producto.getNombre().equals(nombre)){
                return producto.getPrecio();
            }
        }
        return 0;
    }


    public void realizarCompra(Tarjeta tarjeta, Compra compra) {

        boolean repetirSwitch = false;
        int opcion;
        String menu = """
                Le dejo de nuevo la carta ;)
                
                \n\t\t\t-------MENU-------
                \t\t\t1.- Dulces $500
                \t\t\t2.- Chetos $600
                \t\t\t3.- Fruta $300
                \t\t\t------------------
                """;



        do {
            System.out.println(menu);
            try {
                repetirSwitch = true;
            System.out.println("****Agregué sus productos al carrito( Elige el número de tu producto ej. 1 )****");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    productos.add(new Producto("Dulces",500));


                    break;
                case 2:
                    productos.add(new Producto("Chetos",600));


                    break;
                case 3:
                    productos.add(new Producto("Fruta",300));


                    break;
                default:
                    System.out.println("Opción no valida, esa opción no se encuentra dentro del menu, por favor intenta de nuevo");
                    repetirSwitch = false;
                    repetir = "si";
                    break;

            }
        }catch (Exception e){
            System.out.println("No se admiten otro tipos de caracteres que no sean números, intenta de nuevo.");

            repetirSwitch = false;
            repetir = "si";
             scanner.next(); //Limpiamos el buffer

        }
            if (repetirSwitch){ //Cuando haya una Excepción, no preguntará la siguiente línea y se repetira de nuevo el ciclo.
               comprarOtroProducto();
            }


        } while (repetir.toLowerCase().equals("si"));


    }
    public void comprarOtroProducto(){
        System.out.println("¿Desea agregar otra cosa al carrito? (Si no desea otra cosa, escriba 'No', pero si desea algo más coloque 'si')");
        repetir = scanner.next();
        if (repetir.toLowerCase().equals("si")){
            repetir = "si";
        } else if (repetir.toLowerCase().equals("no")) {
            repetir = "no";
        } else if (!repetir.toLowerCase().equals("no") || !repetir.toLowerCase().equals("si")) {
            System.out.println("Ingresa una respuesta valida para avanzar");
            comprarOtroProducto();
        }
    }

    public void mostrarDetallesDeCompra (Tarjeta tarjeta, Compra compra){
        System.out.println("-----------!!TICKET!!-----------");
        System.out.println("Detalles de la compra:\n");
        tarjeta.pagar(tarjeta, productos , compra);
        mostrarProductosComprados();
        System.out.println("\nTotal de productos comprados:" + compra.getCosasCompradas());
        System.out.println("Total:......................$" + compra.getTotalDeCuentaDeCompra());
        System.out.println("Saldo restante:.............$" + tarjeta.getCredito());
        System.out.println("Saldo restante del limite:..$" + tarjeta.getLimite() + "\n");
        compra.setCosasCompradas(0);
        compra.setTotalDeCuentaDeCompra(0);
        System.out.println("-----------!!Gracias!!----------\n");
    }


}
