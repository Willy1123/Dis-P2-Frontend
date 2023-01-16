package com.dis.javalovers.gestionZonasBasicasSalud.model;

import lombok.*;
import java.util.Date;

/**
 * Clase para modelar los datos recibidos del JSON ZonasBasicasSalud
 */
// Cambiar todos los arrobas de abajo por @Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class ZonaBasicaSalud {
    private String codigo_geometria;
    private String zona_basica_salud;
    private Float tasa_incidencia_acumulada_ultimos_14dias;
    private Float tasa_incidencia_acumulada_total;
    private int casos_confirmados_totales;
    private int casos_confirmados_ultimos_14dias;
    private String fecha_informe;

}
