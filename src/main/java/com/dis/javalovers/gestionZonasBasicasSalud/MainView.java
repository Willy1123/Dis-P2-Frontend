package com.dis.javalovers.gestionZonasBasicasSalud;

import com.dis.javalovers.gestionZonasBasicasSalud.view.ZbsMayoresView;
import com.dis.javalovers.gestionZonasBasicasSalud.view.ZbsView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Tag("main-view")
@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends Div{

    private final Tab zbs;
    private final Tab zbsMayores;
    private final VerticalLayout content = new VerticalLayout();
    ZbsView zbsView = new ZbsView();
    ZbsMayoresView zbsMayoresView = new ZbsMayoresView();

    public MainView() {

        zbs = new Tab("Centros Básicos de Salud");
        zbsMayores = new Tab("CBS Mayores");

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
            content.add(zbsView);
        } else if (tab.equals(zbsMayores)) {
            content.add(zbsMayoresView);
        }
    }


}
