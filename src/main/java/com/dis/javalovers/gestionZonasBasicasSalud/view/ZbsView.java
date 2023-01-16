package com.dis.javalovers.gestionZonasBasicasSalud.view;

import com.dis.javalovers.gestionZonasBasicasSalud.ContactForm;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

@Route(value = "ZbsView")
@PageTitle("Tab 1 | Zonas Básicas Salud")
public class ZbsView extends VerticalLayout {

    Grid<ZonaBasicaSalud> grid = new Grid<>(ZonaBasicaSalud.class, false);
    TextField filtro = new TextField();
    ContactForm form = new ContactForm();
    HorizontalLayout results = new HorizontalLayout();
    @Autowired ZBS_Service service;
    public ZbsView(){
        addClassName("zbs-view");
        GridConfig();
        FormConfig();

        add(getToolbar(), getResultado());
    }

    private HorizontalLayout getResultado() {

        results.add(grid, form);
        return results;
    }

    private HorizontalLayout getToolbar() {
        filtro.setPlaceholder("Filter by name...");
        filtro.setClearButtonVisible(true);
        filtro.setValueChangeMode(ValueChangeMode.LAZY);

        Button botonZBS_Actualizar = new Button("Actualizar",
                e -> {
                    try {
                        results.removeAll();
                        grid.setItems(service.leeZBS());
                        getResultado();
                    } catch (Exception ex) {
                        System.err.println("Error al pulsar actualizar");
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
        grid.addColumn(ZonaBasicaSalud::getFecha_informe).setHeader("Fecha informe").setSortable(true);
        grid.setWidth("800px");
        grid.setHeight("600px");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void FormConfig() {
        form.setWidth("20em");
    }

}
