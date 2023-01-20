package com.dis.javalovers.gestionZonasBasicasSalud.backRequest;

import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class API {

    private static final String urlPrefix = "http://localhost:8081/%s";

    // TAB 1
    public String getZBS() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix,"ZonasBasicasSalud");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        return response.body();
    }

    public void postZBS(boolean nuevoElemento, int posicion, ZonaBasicaSalud zbs) throws IOException, InterruptedException {
        URL url = new URL("http://localhost:8081/ZonaBasicaSalud");

        // Abrir una conexión a la URL
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Establecer el método de la petición como POST
        con.setRequestMethod("POST");

        // Establecer la cabeza para enviar un JSON
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        // Habilitar el envío de datos
        con.setDoOutput(true);

        // Crear un objeto JSON con los datos a enviar
        ZonaBasicaSalud zbSalud = new ZonaBasicaSalud();

        JsonObject json = new JsonObject();
        json.addProperty("nuevoCampo", nuevoElemento);
        json.addProperty("indice", posicion);
        json.add("getDAtosZBS", new Gson().toJsonTree(zbSalud));

        // Escribir los datos en la conexión
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Leer la respuesta del servidor
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            // Manejar el error
            System.err.println("Error al mandar el post");
            System.out.println(status);
        }

    }

    // TAB 2
    public String getZBS_Mayores() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSaludMayores60");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        return response.body();
    }
}
