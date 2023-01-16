package com.dis.javalovers.gestionZonasBasicasSalud.controller;

import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;
import com.vaadin.flow.component.grid.Grid;

public class ZBSController{
    public Grid get_tablaZBS() {

        Grid<ZonaBasicaSalud> grid = new Grid<>(ZonaBasicaSalud.class, false);
        grid.addColumn(ZonaBasicaSalud::getCodigo_geometria).setHeader("Codigo geometria");
        grid.addColumn(ZonaBasicaSalud::getZona_basica_salud).setHeader("Zona basica salud");
        grid.addColumn(ZonaBasicaSalud::getTasa_incidencia_acumulada_ultimos_14dias).setHeader("Tasa incidencia acumulada ultimos 14 dias");
        grid.addColumn(ZonaBasicaSalud::getTasa_incidencia_acumulada_total).setHeader("Tasa incidencia acumulada total");
        grid.addColumn(ZonaBasicaSalud::getCasos_confirmados_totales).setHeader("Casos confirmados totales");
        grid.addColumn(ZonaBasicaSalud::getCasos_confirmados_ultimos_14dias).setHeader("Casos confirmados ultimos 14 dias");
        grid.addColumn(ZonaBasicaSalud::getFecha_informe).setHeader("Fecha informe");
        grid.setWidth("600px");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        return grid;
    }
}
