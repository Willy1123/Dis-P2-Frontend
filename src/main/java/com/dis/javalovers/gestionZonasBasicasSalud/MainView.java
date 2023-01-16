package com.dis.javalovers.gestionZonasBasicasSalud;

import com.dis.javalovers.gestionZonasBasicasSalud.controller.ZBSController;
import com.dis.javalovers.gestionZonasBasicasSalud.controller.ZBSMayoresController;
import com.dis.javalovers.gestionZonasBasicasSalud.service.ZBS_Service;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends Div {

    private final Tab zbs;
    private final Tab zbsMayores;
    private final VerticalLayout content;
    private final VerticalLayout results;
    private final HorizontalLayout formulario;
    private final HorizontalLayout botones;

    public MainView(@Autowired ZBS_Service service) throws URISyntaxException, IOException, InterruptedException {

        botones = new HorizontalLayout();

        formulario = new HorizontalLayout();

        results = new VerticalLayout();

        content = new VerticalLayout();

        zbs = new Tab("Centros Básicos de Salud");
        zbsMayores = new Tab("CBS Mayores");

        ContactForm form = new ContactForm();
        form.setWidth("25em");

        ZBSController ZBSController = new ZBSController();
        Grid grid = ZBSController.get_tablaZBS();
        grid.setItems(service.leeZBS());

        Button botonZBS_Actualizar = new Button("Actualizar",
                e -> {
                    try {
                        results.removeAll();
                        grid.setItems(service.leeZBS());
                        formulario.add(grid, form);
                        results.add(formulario);
                    } catch (Exception ex) {
                        System.err.println("Error fatal XD");
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
        botonZBS_New.addThemeVariants(ButtonVariant.LUMO_ICON);

        botones.add(botonZBS_Actualizar, botonZBS_New);

        formulario.add(grid, form);
        results.add(formulario);

        Tabs tabs = new Tabs(zbs, zbsMayores);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );
        setContent(tabs.getSelectedTab());
        add(tabs, content);
    }

    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(zbs)) {
            content.add(botones, results);
        } else if (tab.equals(zbsMayores)) {
            ZBSMayoresController zbsMayores = new ZBSMayoresController();
            content.add(zbsMayores.centroBasicoSaludMayores());
        }
    }
}
