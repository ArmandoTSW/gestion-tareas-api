package com.project.gestiontareasapi.mapper;

import com.project.gestiontareasapi.entity.Tarea;
import com.project.gestiontareasapi.model.TareaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TareaMapper {

    @Mapping(source = "columna.id", target = "columnaId")
    @Mapping(source = "desarrollador.id", target = "desarrolladorId")
    @Mapping(source = "prioridad.id", target = "prioridadId")
    TareaModel toModel(Tarea tarea);

    @Mapping(target = "columna", ignore = true)
    @Mapping(target = "desarrollador", ignore = true)
    @Mapping(target = "prioridad", ignore = true)
    Tarea toEntity(TareaModel tareaModel);
}