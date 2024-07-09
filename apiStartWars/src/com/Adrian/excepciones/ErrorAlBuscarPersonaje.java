package com.Adrian.excepciones;

public class ErrorAlBuscarPersonaje extends Throwable{
    String mensaje;
    public ErrorAlBuscarPersonaje(String s) {
        this.mensaje = s;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
