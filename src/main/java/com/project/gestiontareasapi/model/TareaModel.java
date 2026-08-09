package com.project.gestiontareasapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TareaModel {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Boolean completada;
    private Integer columnaId;
    private Integer desarrolladorId;
    private Integer prioridadId;
}
