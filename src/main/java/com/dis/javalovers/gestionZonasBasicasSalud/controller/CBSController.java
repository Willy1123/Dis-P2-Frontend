package com.dis.javalovers.gestionZonasBasicasSalud.controller;

import com.dis.javalovers.gestionZonasBasicasSalud.GreetService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CBSController {

    public VerticalLayout centroBasicoSalud() {

        HorizontalLayout zonasBasicas = new HorizontalLayout();
        VerticalLayout zonasBasicasContenedor = new VerticalLayout();

        // Use TextField for standard text input
        TextField zonasBasicasLabel = new TextField("Pestaña 1");
        zonasBasicasLabel.addThemeName("bordered");

        zonasBasicas.add(zonasBasicasLabel);


        // Button click listeners can be defined as lambda expressions
        GreetService greetService = new GreetService();
        Button button1 = new Button("Pestaña 1",
                e -> Notification.show(greetService.greet(zonasBasicasLabel.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button1.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        //addClassName("centered-content");

        zonasBasicasContenedor.add(zonasBasicas, button1);
        return zonasBasicasContenedor;
    }
}
