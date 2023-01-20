package com.dis.javalovers.gestionZonasBasicasSalud.view;

import com.dis.javalovers.gestionZonasBasicasSalud.forms.ZBS_Form;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;
import com.dis.javalovers.gestionZonasBasicasSalud.service.ZBS_Service;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "ZbsView")
@PageTitle("Tab 1 | Zonas Básicas Salud")
public class ZbsView extends VerticalLayout {
    Grid<ZonaBasicaSalud> grid = new Grid<>(ZonaBasicaSalud.class, false);
    TextField filtro = new TextField();
    ZBS_Form form = new ZBS_Form();
    HorizontalLayout results = new HorizontalLayout();
    ZBS_Service servicio;
    public ZbsView(ZBS_Service service){
        servicio = service;
        removeAll();
        addClassName("zbs-view");
        GridConfig();
        FormConfig();
        try {
            grid.setItems(servicio.leeZBS());
        } catch (Exception ex) {
            System.err.println("Error al cargar la tabla ZBS");
            System.err.println(ex.getMessage());
        }

        add(getToolbar(), getResultado());
    }

    private HorizontalLayout getResultado() {
        results.removeAll();
        results.add(grid, form);
        return results;
    }

    private HorizontalLayout getToolbar() {
        filtro.setPlaceholder("Filtro por Cod_Geo...");
        filtro.setClearButtonVisible(true);
        filtro.setValueChangeMode(ValueChangeMode.LAZY);

        filtro.addValueChangeListener(event -> {
           String valorCodGeom = event.getValue();
            List<ZonaBasicaSalud> filtroCodigo = null;
            try {
                filtroCodigo = servicio.leeZBS().stream()
                        .filter(item -> item.getCodigo_geometria().contains(valorCodGeom))
                        .collect(Collectors.toList());
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            grid.setItems(filtroCodigo);
        });

        Button botonZBS_Actualizar = new Button("Actualizar",
                e -> {
                    try {
                        grid.setItems(servicio.leeZBS());
                        add(getResultado());
                        filtro.clear();
                        limpiarForm();
                    } catch (Exception ex) {
                        System.err.println("Error al pulsar actualizar");
                        System.err.println(ex.getMessage());
                    }
                });
        botonZBS_Actualizar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button botonZBS_New = new Button("Nuevo",
                e -> {
                    try {
//                        results.removeAll();
//                        grid.setItems(service.leeZBS());
//                        results.add(grid);
                    } catch (Exception ex) {
                        System.err.println("Error fatal XD");
                    }
                });

        HorizontalLayout toolbar = new HorizontalLayout(filtro, botonZBS_Actualizar, botonZBS_New);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void GridConfig() {
        grid.addColumn(ZonaBasicaSalud::getCodigo_geometria).setHeader("Codigo geometria").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getZona_basica_salud).setHeader("Zona basica salud").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getTasa_incidencia_acumulada_ultimos_14dias).setHeader("Tasa incidencia acumulada ultimos 14 dias").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getTasa_incidencia_acumulada_total).setHeader("Tasa incidencia acumulada total").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getCasos_confirmados_totales).setHeader("Casos confirmados totales").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getCasos_confirmados_ultimos_14dias).setHeader("Casos confirmados ultimos 14 dias").setSortable(true);
        grid.addColumn(ZonaBasicaSalud::getFecha_informe) .setHeader("Fecha informe").setSortable(true);
        grid.setWidth("800px");
        grid.setHeight("600px");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.addItemDoubleClickListener(event -> {
            ZonaBasicaSalud zbs_Seleccionado = event.getItem();
            form.getZona_basica_salud().setValue(zbs_Seleccionado.getZona_basica_salud());
            form.getTasa_incidencia_acumulada_ultimos_14dias().setValue(String.valueOf(zbs_Seleccionado.getTasa_incidencia_acumulada_ultimos_14dias()));
            form.getTasa_incidencia_acumulada_total().setValue(String.valueOf(zbs_Seleccionado.getTasa_incidencia_acumulada_total()));
            form.getCasos_confirmados_totales().setValue(String.valueOf(zbs_Seleccionado.getCasos_confirmados_totales()));
            form.getCasos_confirmados_ultimos_14dias().setValue(String.valueOf(zbs_Seleccionado.getCasos_confirmados_ultimos_14dias()));
            LocalDateTime date = LocalDateTime.parse(zbs_Seleccionado.getFecha_informe(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            form.getFecha_informe().setValue(date);
        });
    }

    private void FormConfig() {
        form.setWidth("20em");
    }

    private void limpiarForm() {
        form.getZona_basica_salud().clear();
        form.getTasa_incidencia_acumulada_ultimos_14dias().clear();
        form.getTasa_incidencia_acumulada_total().clear();
        form.getCasos_confirmados_totales().clear();
        form.getCasos_confirmados_ultimos_14dias().clear();
        form.getFecha_informe().clear();
    }

}
