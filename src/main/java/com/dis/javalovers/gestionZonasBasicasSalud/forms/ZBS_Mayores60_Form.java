package com.dis.javalovers.gestionZonasBasicasSalud.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ZBS_Mayores60_Form extends FormLayout {
    TextField zona_basica_salud = new TextField("Zona Básica de Salud");
    TextField tasa_incidencia_acumulada_P60mas_ultimos_14dias = new TextField("Tasa Incidencia ac (Últimos 14 días)");
    TextField casos_confirmados_P60mas_ultimos_14dias = new TextField("Casos confirmados (Últimos 14 días)");
    DatePicker fecha_informe = new DatePicker("Fecha informe");

    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public ZBS_Mayores60_Form() {
        addClassName("contact-form");

        add(zona_basica_salud,
                tasa_incidencia_acumulada_P60mas_ultimos_14dias,
                casos_confirmados_P60mas_ultimos_14dias,
                fecha_informe,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_ERROR);

        //save.addClickShortcut(Key.ENTER);
        //close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }
}
