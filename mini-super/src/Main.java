import com.AdrianTech.Modelos.*;

import java.util.Scanner;

public class Main {
    private static String comprarDeNuevo;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        double limiteDeTarjeta = 0;
        String menu = """
                \n\t\t\t-------MENU-------
                \t\t\t1.- Dulces $500
                \t\t\t2.- Chetos $600
                \t\t\t3.- Fruta $300
                \t\t\t------------------
                """;
        System.out.println(menu);
        System.out.println("Antes de comprar, ingrese un limite para su tarjeta (ej. $ 1800)");
        Tarjeta tarjeta = new Tarjeta(5000,limiteDeTarjeta);
        establecerLimite(tarjeta);



        do {

            Compra compras = new Compra();
            compras.realizarCompra(tarjeta,compras);
            compras.mostrarDetallesDeCompra(tarjeta,compras);
            volveraComprar();
            if (comprarDeNuevo.toLowerCase().equals("si")) {
                System.out.println("Antes de comprar de nuevo, ingrese un nuevo limite para su tarjeta (ej. $ 1800)");
                establecerLimite(tarjeta);
            }else {
                System.out.print("De acuerdo ;).");
            }

        }while (comprarDeNuevo.toLowerCase().equals("si"));


        System.out.println(" Muchas gracias por comprar!! Vuelva pronto :)");



    }
    public static void establecerLimite(Tarjeta tarjeta){


        System.out.println("Su credito actual es de : $" + tarjeta.getCredito());
        System.out.print("Límite : $");
        try {
            tarjeta.setLimite(scanner.nextDouble());
        }catch (Exception e){
            System.out.println("No se admiten letras u otros caracteres, intenta introducir solo números");
            scanner.next(); //Limpiamos el buffer
            establecerLimite(tarjeta);

        }
    }
    public static void volveraComprar(){

        System.out.println("¿Le gustaría volver a comprar?(Si no desea otra cosa, escriba 'No', pero si desea algo más coloque 'si')");
        comprarDeNuevo = scanner.next();
        if (comprarDeNuevo.toLowerCase().equals("si")){
            comprarDeNuevo = "si";
        } else if (comprarDeNuevo.toLowerCase().equals("no")) {
            comprarDeNuevo = "no";
        } else if (!comprarDeNuevo.toLowerCase().equals("no") || !comprarDeNuevo.toLowerCase().equals("si")) {
            System.out.println("Ingresa una respuesta valida para avanzar");
            volveraComprar();
        }
    }
}