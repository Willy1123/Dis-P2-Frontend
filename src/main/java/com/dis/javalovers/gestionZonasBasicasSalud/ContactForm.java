package com.dis.javalovers.gestionZonasBasicasSalud;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ContactForm extends FormLayout {
    TextField zona_basica_salud = new TextField("Zona Básica de Salud");
    TextField tasa_incidencia_acumulada_ultimos_14dias = new TextField("Tasa Incidencia ac (Últimos 14 días)");
    TextField tasa_incidencia_acumulada_total = new TextField("Tasa Incidencia Total Acumulada");
    TextField casos_confirmados_totales = new TextField("Casos confirmados totales");
    TextField casos_confirmados_ultimos_14dias = new TextField("Casos confirmados (Últimos 14 días)");
    DatePicker fecha_informe = new DatePicker("Fecha informe");

    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public ContactForm() {
        addClassName("contact-form");

        add(zona_basica_salud,
                tasa_incidencia_acumulada_ultimos_14dias,
                tasa_incidencia_acumulada_total,
                casos_confirmados_totales,
                casos_confirmados_ultimos_14dias,
                fecha_informe);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        //save.addClickShortcut(Key.ENTER);
        //close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }
}
