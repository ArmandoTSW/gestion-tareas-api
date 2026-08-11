package com.project.gestiontareasapi.controller;

import com.project.gestiontareasapi.model.DesarrolladorModel;
import com.project.gestiontareasapi.service.DesarrolladorService;
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
@RequestMapping("/api/desarrolladores")
@RequiredArgsConstructor
@Tag(name = "Desarrolladores", description = "CRUD de desarrolladores")
public class DesarrolladorController {

    private final DesarrolladorService desarrolladorService;

    @Operation(summary = "Obtener todos los desarrolladores")
    @ApiResponse(responseCode = "200", description = "Lista de desarrolladores obtenida")
    @GetMapping
    public ResponseEntity<List<DesarrolladorModel>> obtenerTodos() {
        return ResponseEntity.ok(desarrolladorService.obtenerTodos());
    }

    @Operation(summary = "Obtener un desarrollador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desarrollador encontrado"),
            @ApiResponse(responseCode = "404", description = "Desarrollador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DesarrolladorModel> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(desarrolladorService.obtenerPorId(id));
    }

    @Operation(summary = "Crear un desarrollador")
    @ApiResponse(responseCode = "201", description = "Desarrollador creado")
    @PostMapping
    public ResponseEntity<DesarrolladorModel> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del desarrollador",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"nombre\":\"Ana López\",\"email\":\"ana@email.com\"}"
                            )
                    )
            )
            @RequestBody DesarrolladorModel desarrolladorModel) {

        return new ResponseEntity<>(desarrolladorService.guardar(desarrolladorModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un desarrollador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desarrollador actualizado"),
            @ApiResponse(responseCode = "404", description = "Desarrollador no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DesarrolladorModel> actualizar(
            @PathVariable Integer id,
            @RequestBody DesarrolladorModel desarrolladorModel) {

        return ResponseEntity.ok(desarrolladorService.actualizar(id, desarrolladorModel));
    }

    @Operation(summary = "Eliminar un desarrollador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Desarrollador eliminado"),
            @ApiResponse(responseCode = "404", description = "Desarrollador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        desarrolladorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}