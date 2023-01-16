package com.dis.javalovers.gestionZonasBasicasSalud.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ZbsMayoresView")
@PageTitle("Tab 2 | Zonas Básicas Salud Mayores")
public class ZbsMayoresView extends VerticalLayout {
    public ZbsMayoresView() {

        HorizontalLayout zonasBasicasMayores = new HorizontalLayout();
        VerticalLayout zonasBasicasMayoresContenedor = new VerticalLayout();

        // Use TextField for standard text input
        TextField zonasBasicasLabel = new TextField("Pestaña 2");
        zonasBasicasLabel.addThemeName("bordered");

        zonasBasicasMayores.add(zonasBasicasLabel);


        // Button click listeners can be defined as lambda expressions
        //GreetService greetService = new GreetService();
        Button button2 = new Button("Pestaña 2",
                e -> System.out.println("pepe"));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button2.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        //addClassName("centered-content");

        add(zonasBasicasMayores, button2);
    }
}
