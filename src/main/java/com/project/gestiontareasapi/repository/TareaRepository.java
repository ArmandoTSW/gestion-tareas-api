package com.project.gestiontareasapi.repository;

import com.project.gestiontareasapi.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}