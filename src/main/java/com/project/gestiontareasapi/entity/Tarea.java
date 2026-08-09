package com.project.gestiontareasapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String descripcion;

    private Boolean completada;

    @ManyToOne
    @JoinColumn(name = "columna_id")
    private Columna columna;

    @ManyToOne
    @JoinColumn(name = "desarrollador_id")
    private Desarrollador desarrollador;

    @ManyToOne
    @JoinColumn(name = "prioridad_id")
    private Prioridad prioridad;
}