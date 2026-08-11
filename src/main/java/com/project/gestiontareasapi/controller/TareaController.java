package com.project.gestiontareasapi.controller;

import com.project.gestiontareasapi.model.TareaModel;
import com.project.gestiontareasapi.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
@Tag(name = "Tareas", description = "CRUD de tareas")
public class TareaController {

    private final TareaService tareaService;

    @Operation(summary = "Obtener todas las tareas")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida")
    @GetMapping
    public ResponseEntity<List<TareaModel>> obtenerTodos() {
        return ResponseEntity.ok(tareaService.obtenerTodos());
    }

    @Operation(summary = "Obtener una tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TareaModel> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tareaService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada"),
            @ApiResponse(responseCode = "404", description = "Columna, desarrollador o prioridad no encontrada")
    })
    @PostMapping
    public ResponseEntity<TareaModel> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la tarea",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"titulo\":\"Crear base de datos\",\"descripcion\":\"Crear las tablas en PostgreSQL\",\"completada\":false,\"columnaId\":1,\"desarrolladorId\":1,\"prioridadId\":1}"
                            )
                    )
            )
            @RequestBody TareaModel tareaModel) {

        return new ResponseEntity<>(tareaService.guardar(tareaModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada"),
            @ApiResponse(responseCode = "404", description = "Tarea o relación no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TareaModel> actualizar(
            @PathVariable Integer id,
            @RequestBody TareaModel tareaModel) {

        return ResponseEntity.ok(tareaService.actualizar(id, tareaModel));
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea eliminada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}