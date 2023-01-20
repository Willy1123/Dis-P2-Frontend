package com.dis.javalovers.gestionZonasBasicasSalud.view;

import com.dis.javalovers.gestionZonasBasicasSalud.forms.ZBS_Mayores60_Form;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud_60;
import com.dis.javalovers.gestionZonasBasicasSalud.service.ZBS_Mayores60_Service;
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

@Route(value = "ZbsMayoresView")
@PageTitle("Tab 2 | Zonas Básicas Salud Mayores 60")
public class ZbsMayoresView extends VerticalLayout {

    Grid<ZonaBasicaSalud_60> gridMayores = new Grid<>(ZonaBasicaSalud_60.class, false);
    TextField filtro = new TextField();
    ZBS_Mayores60_Form formMayores = new ZBS_Mayores60_Form();
    HorizontalLayout results = new HorizontalLayout();
    ZBS_Mayores60_Service servicio;
    public ZbsMayoresView(ZBS_Mayores60_Service service){
        servicio = service;
        removeAll();
        addClassName("zbs-view");
        gridMayoresConfig();
        formMayoresConfig();
        try {
            gridMayores.setItems(servicio.leeZBS_Mayores60());
        } catch (Exception ex) {
            System.err.println("Error al cargar la tabla ZBS Mayores");
            System.err.println(ex.getMessage());
        }

        add(getToolbar(), getResultado());
    }

    private HorizontalLayout getResultado() {
        results.removeAll();
        results.add(gridMayores, formMayores);
        return results;
    }

    private HorizontalLayout getToolbar() {
        filtro.setPlaceholder("Filtro por Cod_Geo...");
        filtro.setClearButtonVisible(true);
        filtro.setValueChangeMode(ValueChangeMode.LAZY);

        filtro.addValueChangeListener(event -> {
            String valorCodGeomMayores = event.getValue();
            List<ZonaBasicaSalud_60> filtroCodigoMayores = null;
            try {
                filtroCodigoMayores = servicio.leeZBS_Mayores60().stream()
                        .filter(item -> item.getCodigo_geometria().contains(valorCodGeomMayores))
                        .collect(Collectors.toList());
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            gridMayores.setItems(filtroCodigoMayores);
        });

        Button botonZBS_Mayores_Actualizar = new Button("Actualizar",
                e -> {
                    try {
                        gridMayores.setItems(servicio.leeZBS_Mayores60());
                        add(getResultado());
                        filtro.clear();
                        limpiarFormMayores();
                    } catch (Exception ex) {
                        System.err.println("Error al pulsar actualizar");
                        System.err.println(ex.getMessage());
                    }
                });
        botonZBS_Mayores_Actualizar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(filtro, botonZBS_Mayores_Actualizar);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void gridMayoresConfig() {
        gridMayores.addColumn(ZonaBasicaSalud_60::getCodigo_geometria).setHeader("Codigo geometria").setSortable(true);
        gridMayores.addColumn(ZonaBasicaSalud_60::getZona_basica_salud).setHeader("Zona basica salud").setSortable(true);
        gridMayores.addColumn(ZonaBasicaSalud_60::getTasa_incidencia_acumulada_P60mas_ultimos_14dias).setHeader("Tasa incidencia acumulada ultimos 14 dias").setSortable(true);
        gridMayores.addColumn(ZonaBasicaSalud_60::getCasos_confirmados_P60mas_ultimos_14dias).setHeader("Casos confirmados ultimos 14 dias").setSortable(true);
        gridMayores.addColumn(ZonaBasicaSalud_60::getFecha_informe).setHeader("Fecha informe").setSortable(true);
        gridMayores.setWidth("800px");
        gridMayores.setHeight("600px");
        gridMayores.getColumns().forEach(column -> column.setAutoWidth(true));

        gridMayores.addItemDoubleClickListener(event -> {
            ZonaBasicaSalud_60 zbsMayores_Seleccionado = event.getItem();
            formMayores.getZona_basica_salud().setValue(zbsMayores_Seleccionado.getZona_basica_salud());
            formMayores.getTasa_incidencia_acumulada_P60mas_ultimos_14dias().setValue(String.valueOf(zbsMayores_Seleccionado.getTasa_incidencia_acumulada_P60mas_ultimos_14dias()));
            formMayores.getCasos_confirmados_P60mas_ultimos_14dias().setValue(String.valueOf(zbsMayores_Seleccionado.getCasos_confirmados_P60mas_ultimos_14dias()));
            LocalDateTime date = LocalDateTime.parse(zbsMayores_Seleccionado.getFecha_informe(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            formMayores.getFecha_informe().setValue(date);
        });
    }

    private void formMayoresConfig() {
        formMayores.setWidth("20em");
    }

    private void limpiarFormMayores() {
        formMayores.getZona_basica_salud().clear();
        formMayores.getTasa_incidencia_acumulada_P60mas_ultimos_14dias().clear();
        formMayores.getCasos_confirmados_P60mas_ultimos_14dias().clear();
        formMayores.getFecha_informe().clear();
    }

}
