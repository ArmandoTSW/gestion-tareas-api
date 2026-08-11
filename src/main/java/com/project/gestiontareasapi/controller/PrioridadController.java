package com.project.gestiontareasapi.controller;

import com.project.gestiontareasapi.model.PrioridadModel;
import com.project.gestiontareasapi.service.PrioridadService;
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
@RequestMapping("/api/prioridades")
@RequiredArgsConstructor
@Tag(name = "Prioridades", description = "CRUD de prioridades")
public class PrioridadController {

    private final PrioridadService prioridadService;

    @Operation(summary = "Obtener todas las prioridades")
    @ApiResponse(responseCode = "200", description = "Lista de prioridades obtenida")
    @GetMapping
    public ResponseEntity<List<PrioridadModel>> obtenerTodos() {
        return ResponseEntity.ok(prioridadService.obtenerTodos());
    }

    @Operation(summary = "Obtener una prioridad por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prioridad encontrada"),
            @ApiResponse(responseCode = "404", description = "Prioridad no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrioridadModel> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(prioridadService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una prioridad")
    @ApiResponse(responseCode = "201", description = "Prioridad creada")
    @PostMapping
    public ResponseEntity<PrioridadModel> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la prioridad",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"nombre\":\"Alta\",\"descripcion\":\"Tarea urgente\"}"
                            )
                    )
            )
            @RequestBody PrioridadModel prioridadModel) {

        return new ResponseEntity<>(prioridadService.guardar(prioridadModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una prioridad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prioridad actualizada"),
            @ApiResponse(responseCode = "404", description = "Prioridad no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PrioridadModel> actualizar(
            @PathVariable Integer id,
            @RequestBody PrioridadModel prioridadModel) {

        return ResponseEntity.ok(prioridadService.actualizar(id, prioridadModel));
    }

    @Operation(summary = "Eliminar una prioridad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Prioridad eliminada"),
            @ApiResponse(responseCode = "404", description = "Prioridad no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        prioridadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}