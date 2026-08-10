package com.project.gestiontareasapi.mapper;

import com.project.gestiontareasapi.entity.Proyecto;
import com.project.gestiontareasapi.model.ProyectoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProyectoMapper {

    ProyectoModel toModel(Proyecto proyecto);

    @Mapping(target = "columnas", ignore = true)
    Proyecto toEntity(ProyectoModel proyectoModel);
}