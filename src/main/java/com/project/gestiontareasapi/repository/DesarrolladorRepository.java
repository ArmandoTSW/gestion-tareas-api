package com.project.gestiontareasapi.repository;

import com.project.gestiontareasapi.entity.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Integer> {
}