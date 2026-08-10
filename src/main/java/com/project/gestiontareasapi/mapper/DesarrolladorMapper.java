package com.project.gestiontareasapi.mapper;

import com.project.gestiontareasapi.entity.Desarrollador;
import com.project.gestiontareasapi.model.DesarrolladorModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesarrolladorMapper {

    DesarrolladorModel toModel(Desarrollador desarrollador);

    Desarrollador toEntity(DesarrolladorModel desarrolladorModel);
}