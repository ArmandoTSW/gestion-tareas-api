package com.project.gestiontareasapi.mapper;

import com.project.gestiontareasapi.entity.Columna;
import com.project.gestiontareasapi.model.ColumnaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColumnaMapper {

    @Mapping(source = "proyecto.id", target = "proyectoId")
    ColumnaModel toModel(Columna columna);

    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "tareas", ignore = true)
    Columna toEntity(ColumnaModel columnaModel);
}