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

        String urlFull = urlPrefix + "ZonasBasicasSaludMayores";
        var values = new HashMap<String, String>() {{
            put("nuevoElemento", String.valueOf(nuevoElemento));
            put("posicion", String.valueOf(posicion));
            put ("getDatosZBS", String.valueOf(zbs));
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFull))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

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
