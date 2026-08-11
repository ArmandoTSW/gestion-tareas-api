package com.project.gestiontareasapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoCompletoModel {

    private String nombre;
    private String descripcion;
    private List<ColumnaModel> columnas;
}