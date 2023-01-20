package com.dis.javalovers.gestionZonasBasicasSalud.view;

import com.dis.javalovers.gestionZonasBasicasSalud.forms.ZBS_Form;
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

        add(getToolbar(), getResultado());
    }

    private HorizontalLayout getResultado() {
        results.removeAll();
        results.add(gridMayores, formMayores);
        return results;
    }

    private HorizontalLayout getToolbar() {
        filtro.setPlaceholder("Filter by name...");
        filtro.setClearButtonVisible(true);
        filtro.setValueChangeMode(ValueChangeMode.LAZY);

        Button botonZBS_Mayores_Actualizar = new Button("Actualizar",
                e -> {
                    try {
                        gridMayores.setItems(servicio.leeZBS_Mayores60());
                        add(getResultado());
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
    }

    private void formMayoresConfig() {
        formMayores.setWidth("20em");
    }

}
