package com.project.gestiontareasapi.service;

import com.project.gestiontareasapi.entity.Desarrollador;
import com.project.gestiontareasapi.exception.RecursoNoEncontradoException;
import com.project.gestiontareasapi.mapper.DesarrolladorMapper;
import com.project.gestiontareasapi.model.DesarrolladorModel;
import com.project.gestiontareasapi.repository.DesarrolladorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesarrolladorService {

    private final DesarrolladorRepository desarrolladorRepository;
    private final DesarrolladorMapper desarrolladorMapper;

    public List<DesarrolladorModel> obtenerTodos() {
        return desarrolladorRepository.findAll()
                .stream()
                .map(desarrolladorMapper::toModel)
                .toList();
    }

    public DesarrolladorModel obtenerPorId(Integer id) {
        Desarrollador desarrollador = desarrolladorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Desarrollador no encontrado"));

        return desarrolladorMapper.toModel(desarrollador);
    }

    public DesarrolladorModel guardar(DesarrolladorModel desarrolladorModel) {
        Desarrollador desarrollador = desarrolladorMapper.toEntity(desarrolladorModel);
        return desarrolladorMapper.toModel(desarrolladorRepository.save(desarrollador));
    }

    public DesarrolladorModel actualizar(Integer id, DesarrolladorModel desarrolladorModel) {
        Desarrollador desarrollador = desarrolladorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Desarrollador no encontrado"));

        desarrollador.setNombre(desarrolladorModel.getNombre());
        desarrollador.setEmail(desarrolladorModel.getEmail());

        return desarrolladorMapper.toModel(desarrolladorRepository.save(desarrollador));
    }

    public void eliminar(Integer id) {
        Desarrollador desarrollador = desarrolladorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Desarrollador no encontrado"));

        desarrolladorRepository.delete(desarrollador);
    }
}