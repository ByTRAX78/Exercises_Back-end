package com.Adrian.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorDeArchivos {

    public void guardarPersonajeTxt(List personajes)throws IOException{

        FileWriter escritura = new FileWriter("Personajes buscados.txt");
        for (Object personaje1: personajes) {
            escritura.write(personaje1.toString() + "\n");
        }
        escritura.close();

    }
    public void guardarPersonajeJson(List datosApis) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("Personajes buscados.json");
        escritura.write("[ \n" + gson.toJson(datosApis.get(0)));
        for (int i = 1; i <datosApis.size(); i++){
            escritura.write(",\n" + gson.toJson(datosApis.get(i)));
        }

        escritura.write("\n]");
        escritura.close();
    }

}
