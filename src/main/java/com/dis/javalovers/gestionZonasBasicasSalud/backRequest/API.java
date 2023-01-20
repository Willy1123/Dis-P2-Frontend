package com.dis.javalovers.gestionZonasBasicasSalud.backRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {

    private static final String urlPrefixTab1 = "http://localhost:8081/ZonasBasicasSalud/";
    private static final String urlPrefixTab2 = "http://localhost:8081/ZonasBasicasSaludMayores60/";

    public String getZBS() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefixTab1);
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

    public String getZBS_Mayores() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefixTab2);
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
