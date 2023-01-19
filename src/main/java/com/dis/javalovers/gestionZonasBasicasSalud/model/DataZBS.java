package com.dis.javalovers.gestionZonasBasicasSalud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class DataZBS {
    private List<ZonaBasicaSalud> data;
}
