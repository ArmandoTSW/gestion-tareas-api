package com.project.gestiontareasapi.service;

import com.project.gestiontareasapi.entity.Columna;
import com.project.gestiontareasapi.entity.Desarrollador;
import com.project.gestiontareasapi.entity.Prioridad;
import com.project.gestiontareasapi.entity.Tarea;
import com.project.gestiontareasapi.exception.RecursoNoEncontradoException;
import com.project.gestiontareasapi.mapper.TareaMapper;
import com.project.gestiontareasapi.model.TareaModel;
import com.project.gestiontareasapi.repository.ColumnaRepository;
import com.project.gestiontareasapi.repository.DesarrolladorRepository;
import com.project.gestiontareasapi.repository.PrioridadRepository;
import com.project.gestiontareasapi.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ColumnaRepository columnaRepository;
    private final DesarrolladorRepository desarrolladorRepository;
    private final PrioridadRepository prioridadRepository;
    private final TareaMapper tareaMapper;

    public List<TareaModel> obtenerTodos() {
        return tareaRepository.findAll()
                .stream()
                .map(tareaMapper::toModel)
                .toList();
    }

    public TareaModel obtenerPorId(Integer id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea no encontrada"));

        return tareaMapper.toModel(tarea);
    }

    public TareaModel guardar(TareaModel tareaModel) {
        Tarea tarea = tareaMapper.toEntity(tareaModel);
        asignarRelaciones(tarea, tareaModel);

        if (tarea.getCompletada() == null) {
            tarea.setCompletada(false);
        }

        return tareaMapper.toModel(tareaRepository.save(tarea));
    }

    public TareaModel actualizar(Integer id, TareaModel tareaModel) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea no encontrada"));

        tarea.setTitulo(tareaModel.getTitulo());
        tarea.setDescripcion(tareaModel.getDescripcion());
        tarea.setCompletada(tareaModel.getCompletada());
        asignarRelaciones(tarea, tareaModel);

        return tareaMapper.toModel(tareaRepository.save(tarea));
    }

    public void eliminar(Integer id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea no encontrada"));

        tareaRepository.delete(tarea);
    }

    private void asignarRelaciones(Tarea tarea, TareaModel tareaModel) {
        Columna columna = columnaRepository.findById(tareaModel.getColumnaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Columna no encontrada"));

        Desarrollador desarrollador = desarrolladorRepository.findById(tareaModel.getDesarrolladorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Desarrollador no encontrado"));

        Prioridad prioridad = prioridadRepository.findById(tareaModel.getPrioridadId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Prioridad no encontrada"));

        tarea.setColumna(columna);
        tarea.setDesarrollador(desarrollador);
        tarea.setPrioridad(prioridad);
    }
}