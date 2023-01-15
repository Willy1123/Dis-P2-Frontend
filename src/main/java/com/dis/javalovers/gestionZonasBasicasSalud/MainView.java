package com.dis.javalovers.gestionZonasBasicasSalud;

import com.dis.javalovers.gestionZonasBasicasSalud.controller.CBSController;
import com.dis.javalovers.gestionZonasBasicasSalud.controller.CBSMayoresController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

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

    private final Tab cbs;
    private final Tab cbsMayores;
    private final VerticalLayout content;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

        content = new VerticalLayout();

        cbs = new Tab("Centros Básicos de Salud");
        cbsMayores = new Tab("CBS Mayores");

        Tabs tabs = new Tabs(cbs, cbsMayores);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );

        setContent(tabs.getSelectedTab());
        add(tabs, content);
    }

    private void setContent(Tab tab) {
        content.removeAll();
        CBSController zbs = new CBSController();
        CBSMayoresController zbsMayores = new CBSMayoresController();

        if (tab.equals(cbs)) {
            content.add(zbs.centroBasicoSalud());
        } else if (tab.equals(cbsMayores)) {
            content.add(zbsMayores.centroBasicoSaludMayores());
        }
    }
}
