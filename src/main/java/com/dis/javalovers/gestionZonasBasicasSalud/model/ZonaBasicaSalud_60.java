package com.dis.javalovers.gestionZonasBasicasSalud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class ZonaBasicaSalud_60 {
        private String codigo_geometria;
        private String zona_basica_salud;
        private Number tasa_incidencia_acumulada_P60mas_ultimos_14dias;
        private Number casos_confirmados_P60mas_ultimos_14dias;
        private String fecha_informe;

}
