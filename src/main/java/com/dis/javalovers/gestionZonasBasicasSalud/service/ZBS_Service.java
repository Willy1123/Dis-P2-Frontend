package com.dis.javalovers.gestionZonasBasicasSalud.service;

import com.dis.javalovers.gestionZonasBasicasSalud.backRequest.API;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class ZBS_Service {

    public List<ZonaBasicaSalud> leeZBS() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZBS();
        Gson gson = new Gson();
        Type tipo = new TypeToken<List<ZonaBasicaSalud>>(){}.getType();
        List<ZonaBasicaSalud> lista = gson.fromJson(resultsAPI,tipo);
        return lista;
    }

    public void cambiarZBS(boolean nuevoElemento, int pos, ZonaBasicaSalud zbs) throws IOException, InterruptedException {
        API api = new API();
        api.postZBS(nuevoElemento, pos, zbs);
    }
}
