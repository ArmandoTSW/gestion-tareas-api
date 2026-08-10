package com.project.gestiontareasapi.mapper;

import com.project.gestiontareasapi.entity.Prioridad;
import com.project.gestiontareasapi.model.PrioridadModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrioridadMapper {

    PrioridadModel toModel(Prioridad prioridad);

    Prioridad toEntity(PrioridadModel prioridadModel);
}