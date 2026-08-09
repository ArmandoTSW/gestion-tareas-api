package com.project.gestiontareasapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnaModel {

    private Integer id;
    private String nombre;
    private Integer proyectoId;
}