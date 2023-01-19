package com.dis.javalovers.gestionZonasBasicasSalud.service;

import com.dis.javalovers.gestionZonasBasicasSalud.backRequest.API;
import com.dis.javalovers.gestionZonasBasicasSalud.model.DataZBS;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class ZBS_Service implements Serializable {

    public List<ZonaBasicaSalud> leeZBS() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZBS();
        Gson gson = new Gson();
        DataZBS lista = gson.fromJson(resultsAPI,new TypeToken<DataZBS>(){}.getType());
        return lista.getData();
    }
}
