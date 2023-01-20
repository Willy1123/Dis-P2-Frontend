package com.dis.javalovers.gestionZonasBasicasSalud.service;

import com.dis.javalovers.gestionZonasBasicasSalud.backRequest.API;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud_60;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ZBS_Mayores60_Service {

    public List<ZonaBasicaSalud_60> leeZBS_Mayores60() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZBS_Mayores();
        Gson gson = new Gson();
        Type tipo = new TypeToken<List<ZonaBasicaSalud_60>>(){}.getType();
        List<ZonaBasicaSalud_60> lista = gson.fromJson(resultsAPI,tipo);
        return lista;
    }
}
