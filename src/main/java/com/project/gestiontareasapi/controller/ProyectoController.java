package com.project.gestiontareasapi.controller;

import com.project.gestiontareasapi.model.ProyectoCompletoModel;
import com.project.gestiontareasapi.model.ProyectoModel;
import com.project.gestiontareasapi.service.ProyectoService;
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
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
@Tag(name = "Proyectos", description = "CRUD de proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    @Operation(summary = "Obtener todos los proyectos")
    @ApiResponse(responseCode = "200", description = "Lista de proyectos obtenida")
    @GetMapping
    public ResponseEntity<List<ProyectoModel>> obtenerTodos() {
        return ResponseEntity.ok(proyectoService.obtenerTodos());
    }

    @Operation(summary = "Obtener un proyecto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProyectoModel> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(proyectoService.obtenerPorId(id));
    }

    @Operation(summary = "Crear un proyecto")
    @ApiResponse(responseCode = "201", description = "Proyecto creado")
    @PostMapping
    public ResponseEntity<ProyectoModel> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del proyecto",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"nombre\":\"Sistema escolar\",\"descripcion\":\"Proyecto para controlar tareas\"}"
                            )
                    )
            )
            @RequestBody ProyectoModel proyectoModel) {

        return new ResponseEntity<>(proyectoService.guardar(proyectoModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProyectoModel> actualizar(
            @PathVariable Integer id,
            @RequestBody ProyectoModel proyectoModel) {

        return ResponseEntity.ok(proyectoService.actualizar(id, proyectoModel));
    }

    @Operation(summary = "Eliminar un proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un proyecto con columnas en cascada")
    @ApiResponse(responseCode = "201", description = "Proyecto y columnas creados")
    @PostMapping("/con-columnas")
    public ResponseEntity<ProyectoModel> guardarConColumnas(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Proyecto con sus columnas",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"nombre\":\"API de tareas\",\"descripcion\":\"Proyecto de ejemplo\",\"columnas\":[{\"nombre\":\"Pendiente\"},{\"nombre\":\"En proceso\"},{\"nombre\":\"Terminado\"}]}"
                            )
                    )
            )
            @RequestBody ProyectoCompletoModel proyectoCompletoModel) {

        return new ResponseEntity<>(
                proyectoService.guardarConColumnas(proyectoCompletoModel),
                HttpStatus.CREATED
        );
    }
}