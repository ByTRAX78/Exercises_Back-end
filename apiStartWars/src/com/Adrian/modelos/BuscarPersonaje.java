package com.Adrian.modelos;


import com.Adrian.excepciones.ErrorAlBuscarPersonaje;
import com.Adrian.modelos.DatosApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscarPersonaje {



    public DatosApi buscarPersona (int numero) throws ErrorAlBuscarPersonaje, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("https://swapi.dev/api/people/"+numero+"/");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();


            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if (response.body().length() > 22) {
                return new Gson().fromJson(response.body(), DatosApi.class);
            }else {
                throw new ErrorAlBuscarPersonaje("No se encontró ese personaje en la petición T_T");
            }




    }



}