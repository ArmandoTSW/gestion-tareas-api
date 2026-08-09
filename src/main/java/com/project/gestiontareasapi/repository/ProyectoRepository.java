package com.project.gestiontareasapi.repository;

import com.project.gestiontareasapi.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}