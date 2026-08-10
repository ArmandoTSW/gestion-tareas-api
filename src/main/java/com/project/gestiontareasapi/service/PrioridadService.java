package com.project.gestiontareasapi.service;

import com.project.gestiontareasapi.entity.Prioridad;
import com.project.gestiontareasapi.exception.RecursoNoEncontradoException;
import com.project.gestiontareasapi.mapper.PrioridadMapper;
import com.project.gestiontareasapi.model.PrioridadModel;
import com.project.gestiontareasapi.repository.PrioridadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrioridadService {

    private final PrioridadRepository prioridadRepository;
    private final PrioridadMapper prioridadMapper;

    public List<PrioridadModel> obtenerTodos() {
        return prioridadRepository.findAll()
                .stream()
                .map(prioridadMapper::toModel)
                .toList();
    }

    public PrioridadModel obtenerPorId(Integer id) {
        Prioridad prioridad = prioridadRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Prioridad no encontrada"));

        return prioridadMapper.toModel(prioridad);
    }

    public PrioridadModel guardar(PrioridadModel prioridadModel) {
        Prioridad prioridad = prioridadMapper.toEntity(prioridadModel);
        return prioridadMapper.toModel(prioridadRepository.save(prioridad));
    }

    public PrioridadModel actualizar(Integer id, PrioridadModel prioridadModel) {
        Prioridad prioridad = prioridadRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Prioridad no encontrada"));

        prioridad.setNombre(prioridadModel.getNombre());
        prioridad.setDescripcion(prioridadModel.getDescripcion());

        return prioridadMapper.toModel(prioridadRepository.save(prioridad));
    }

    public void eliminar(Integer id) {
        Prioridad prioridad = prioridadRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Prioridad no encontrada"));

        prioridadRepository.delete(prioridad);
    }
}