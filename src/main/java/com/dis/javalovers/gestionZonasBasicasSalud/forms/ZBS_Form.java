package com.dis.javalovers.gestionZonasBasicasSalud.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter @Setter
public class ZBS_Form extends FormLayout {
    TextField zona_basica_salud = new TextField("Zona Básica de Salud");
    TextField tasa_incidencia_acumulada_ultimos_14dias = new TextField("Tasa Incidencia ac (Últimos 14 días)");
    TextField tasa_incidencia_acumulada_total = new TextField("Tasa Incidencia Total Acumulada");
    TextField casos_confirmados_totales = new TextField("Casos confirmados totales");
    TextField casos_confirmados_ultimos_14dias = new TextField("Casos confirmados (Últimos 14 días)");
    DateTimePicker fecha_informe = new DateTimePicker();

    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public ZBS_Form() {
        addClassName("contact-form");
        fecha_informe.setLabel("Fecha informe");
        fecha_informe.setStep(Duration.ofMinutes(30));

        add(zona_basica_salud,
                tasa_incidencia_acumulada_ultimos_14dias,
                tasa_incidencia_acumulada_total,
                casos_confirmados_totales,
                casos_confirmados_ultimos_14dias,
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
