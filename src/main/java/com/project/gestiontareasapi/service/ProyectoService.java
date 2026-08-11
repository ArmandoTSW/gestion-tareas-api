package com.project.gestiontareasapi.service;

import com.project.gestiontareasapi.entity.Columna;
import com.project.gestiontareasapi.entity.Proyecto;
import com.project.gestiontareasapi.exception.RecursoNoEncontradoException;
import com.project.gestiontareasapi.mapper.ProyectoMapper;
import com.project.gestiontareasapi.model.ColumnaModel;
import com.project.gestiontareasapi.model.ProyectoCompletoModel;
import com.project.gestiontareasapi.model.ProyectoModel;
import com.project.gestiontareasapi.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;

    public List<ProyectoModel> obtenerTodos() {
        return proyectoRepository.findAll()
                .stream()
                .map(proyectoMapper::toModel)
                .toList();
    }

    public ProyectoModel obtenerPorId(Integer id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado"));

        return proyectoMapper.toModel(proyecto);
    }

    public ProyectoModel guardar(ProyectoModel proyectoModel) {
        Proyecto proyecto = proyectoMapper.toEntity(proyectoModel);
        return proyectoMapper.toModel(proyectoRepository.save(proyecto));
    }

    public ProyectoModel actualizar(Integer id, ProyectoModel proyectoModel) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado"));

        proyecto.setNombre(proyectoModel.getNombre());
        proyecto.setDescripcion(proyectoModel.getDescripcion());

        return proyectoMapper.toModel(proyectoRepository.save(proyecto));
    }

    public void eliminar(Integer id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado"));

        proyectoRepository.delete(proyecto);
    }

    public ProyectoModel guardarConColumnas(ProyectoCompletoModel proyectoCompletoModel) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoCompletoModel.getNombre());
        proyecto.setDescripcion(proyectoCompletoModel.getDescripcion());

        List<Columna> columnas = new ArrayList<>();

        for (ColumnaModel columnaModel : proyectoCompletoModel.getColumnas()) {
            Columna columna = new Columna();
            columna.setNombre(columnaModel.getNombre());
            columna.setProyecto(proyecto);
            columnas.add(columna);
        }

        proyecto.setColumnas(columnas);

        return proyectoMapper.toModel(proyectoRepository.save(proyecto));
    }
}