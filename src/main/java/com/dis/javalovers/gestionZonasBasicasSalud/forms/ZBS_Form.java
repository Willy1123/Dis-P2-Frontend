package com.dis.javalovers.gestionZonasBasicasSalud.forms;

import com.dis.javalovers.gestionZonasBasicasSalud.backRequest.API;
import com.dis.javalovers.gestionZonasBasicasSalud.model.ZonaBasicaSalud;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class ZBS_Form extends FormLayout {

    boolean nuevoElemento;
    int posicion;
    String codigo_geometria;
    TextField zona_basica_salud = new TextField("Zona Básica de Salud");
    TextField tasa_incidencia_acumulada_ultimos_14dias = new TextField("Tasa Incidencia ac (Últimos 14 días)");
    TextField tasa_incidencia_acumulada_total = new TextField("Tasa Incidencia Total Acumulada");
    TextField casos_confirmados_totales = new TextField("Casos confirmados totales");
    TextField casos_confirmados_ultimos_14dias = new TextField("Casos confirmados (Últimos 14 días)");
    DateTimePicker fecha_informe = new DateTimePicker();

    Button save = new Button("Save",
            e -> {
                ZonaBasicaSalud zbs = new ZonaBasicaSalud();
                zbs.setCodigo_geometria(codigo_geometria);
                zbs.setZona_basica_salud(zona_basica_salud.getValue());
                zbs.setTasa_incidencia_acumulada_ultimos_14dias(Float.valueOf(tasa_incidencia_acumulada_ultimos_14dias.getValue()));
                zbs.setTasa_incidencia_acumulada_total(Float.valueOf(tasa_incidencia_acumulada_total.getValue()));
                zbs.setCasos_confirmados_totales(Integer.parseInt(casos_confirmados_totales.getValue()));
                zbs.setCasos_confirmados_ultimos_14dias(Integer.parseInt(casos_confirmados_ultimos_14dias.getValue()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                zbs.setFecha_informe(fecha_informe.getValue().format(formatter));
                API api = new API();
                try {
                    api.postZBS(nuevoElemento, posicion, zbs);
                } catch (Exception ex) {
                    System.err.println("Error al guardar los datos");
                    System.err.println(ex.getMessage());
                }
            });
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
