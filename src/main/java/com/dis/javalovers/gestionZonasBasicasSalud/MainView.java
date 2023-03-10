package com.dis.javalovers.gestionZonasBasicasSalud;

import com.dis.javalovers.gestionZonasBasicasSalud.service.ZBS_Mayores60_Service;
import com.dis.javalovers.gestionZonasBasicasSalud.service.ZBS_Service;
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
import org.springframework.beans.factory.annotation.Autowired;

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
    //ZbsView zbsView = new ZbsView(service);
    //ZbsMayoresView zbsMayoresView = new ZbsMayoresView();

    public MainView(@Autowired ZBS_Service service, ZBS_Mayores60_Service service_mayores) {


        zbs = new Tab("Zonas Básicss de Salud");
        zbsMayores = new Tab("ZBS Mayores de 60");

        Tabs tabs = new Tabs(zbs, zbsMayores);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab(), service, service_mayores)
        );
        setContent(tabs.getSelectedTab(), service, service_mayores);
        add(tabs, content);
    }

    private void setContent(Tab tab, ZBS_Service service, ZBS_Mayores60_Service service_mayores) {
        content.removeAll();

        if (tab.equals(zbs)) {
            ZbsView zbsView = new ZbsView(service);
            content.add(zbsView);
        } else if (tab.equals(zbsMayores)) {
            ZbsMayoresView zbsMayoresView1 = new ZbsMayoresView(service_mayores);
            content.add(zbsMayoresView1);
        }
    }


}
