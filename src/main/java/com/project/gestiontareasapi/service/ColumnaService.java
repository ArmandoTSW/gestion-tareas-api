package com.project.gestiontareasapi.service;

import com.project.gestiontareasapi.entity.Columna;
import com.project.gestiontareasapi.entity.Proyecto;
import com.project.gestiontareasapi.exception.RecursoNoEncontradoException;
import com.project.gestiontareasapi.mapper.ColumnaMapper;
import com.project.gestiontareasapi.model.ColumnaModel;
import com.project.gestiontareasapi.repository.ColumnaRepository;
import com.project.gestiontareasapi.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnaService {

    private final ColumnaRepository columnaRepository;
    private final ProyectoRepository proyectoRepository;
    private final ColumnaMapper columnaMapper;

    public List<ColumnaModel> obtenerTodos() {
        return columnaRepository.findAll()
                .stream()
                .map(columnaMapper::toModel)
                .toList();
    }

    public ColumnaModel obtenerPorId(Integer id) {
        Columna columna = columnaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Columna no encontrada"));

        return columnaMapper.toModel(columna);
    }

    public ColumnaModel guardar(ColumnaModel columnaModel) {
        Proyecto proyecto = proyectoRepository.findById(columnaModel.getProyectoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado"));

        Columna columna = columnaMapper.toEntity(columnaModel);
        columna.setProyecto(proyecto);

        return columnaMapper.toModel(columnaRepository.save(columna));
    }

    public ColumnaModel actualizar(Integer id, ColumnaModel columnaModel) {
        Columna columna = columnaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Columna no encontrada"));

        Proyecto proyecto = proyectoRepository.findById(columnaModel.getProyectoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Proyecto no encontrado"));

        columna.setNombre(columnaModel.getNombre());
        columna.setProyecto(proyecto);

        return columnaMapper.toModel(columnaRepository.save(columna));
    }

    public void eliminar(Integer id) {
        Columna columna = columnaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Columna no encontrada"));

        columnaRepository.delete(columna);
    }
}