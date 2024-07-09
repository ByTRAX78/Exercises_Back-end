package com.Adrian.modelos;

import com.Adrian.excepciones.ErrorAlBuscarPersonaje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidarPersonaje {
    static private Scanner scanner = new Scanner(System.in);
    static List<Personaje> personajes = new ArrayList<>();
    static List<DatosApi> datosApis = new ArrayList<>();

    public static void buscarPersonaje(){
        boolean salir;
        boolean entrar = true;
        try {
            while (entrar){
                var numeroPersonaje = Integer.valueOf(scanner.nextLine());
                BuscarPersonaje buscar = new BuscarPersonaje();
                DatosApi datosApi = buscar.buscarPersona(numeroPersonaje);
                Personaje personaje = new Personaje(datosApi);
                System.out.println("Perfecto!! Aquí estan los datos de tu personaje ;)");
                System.out.println(personaje);
                personajes.add(personaje);
                datosApis.add(datosApi);

                do{
                    salir = false;
                    System.out.println("¿Deseas buscar otro? (Coloca 'si' o 'no' solamente)");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("no")){
                        generarDocumento(personajes,datosApis);
                        System.out.println("De acuerdo. Gracias por usar esta aplicación ;)");
                        entrar = false;

                    }else if(respuesta.equalsIgnoreCase("si")){
                        System.out.println("De acuerdo, busquemos otro ;)");
                        System.out.println("Busca a un personaje colocando un numero entero :)");
                        entrar = true;
                    }else {
                        System.out.println("Respuesta incorrecta, coloca una opción valida");
                        salir = true;
                    }
                }while (salir);

            }

        } catch (NumberFormatException e) {
            System.out.println("No estan permitido usar letras u otros simbolos O_o");
            System.out.println("Busca a un personaje colocando un numero entero :)");
            buscarPersonaje();
        }catch (ErrorAlBuscarPersonaje e){
            System.out.println(e.getMessage());
            System.out.println("Busca a un personaje colocando otro numero entero :)");
            buscarPersonaje();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generarDocumento(List personajes, List datosApis) throws IOException {
        GeneradorDeArchivos generadorDeArchivos = new GeneradorDeArchivos();
        System.out.println("¿Como quieres guardar tus personajes seleccionados?(Coloca solamente Json o Txt)");
        String repuestaFormato = scanner.nextLine();
        if (repuestaFormato.equalsIgnoreCase("json")){
            generadorDeArchivos.guardarPersonajeJson(datosApis);
        } else if (repuestaFormato.equalsIgnoreCase("txt")) {
            generadorDeArchivos.guardarPersonajeTxt(personajes);
        }else {
            System.out.println("Respuesta invalida, intentelo de nuevo");
            generarDocumento(personajes,datosApis);
        }
    }
}
