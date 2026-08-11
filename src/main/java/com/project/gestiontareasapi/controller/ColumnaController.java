package com.project.gestiontareasapi.controller;

import com.project.gestiontareasapi.model.ColumnaModel;
import com.project.gestiontareasapi.service.ColumnaService;
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
@RequestMapping("/api/columnas")
@RequiredArgsConstructor
@Tag(name = "Columnas", description = "CRUD de columnas")
public class ColumnaController {

    private final ColumnaService columnaService;

    @Operation(summary = "Obtener todas las columnas")
    @ApiResponse(responseCode = "200", description = "Lista de columnas obtenida")
    @GetMapping
    public ResponseEntity<List<ColumnaModel>> obtenerTodos() {
        return ResponseEntity.ok(columnaService.obtenerTodos());
    }

    @Operation(summary = "Obtener una columna por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Columna encontrada"),
            @ApiResponse(responseCode = "404", description = "Columna no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColumnaModel> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(columnaService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una columna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Columna creada"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PostMapping
    public ResponseEntity<ColumnaModel> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la columna",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"nombre\":\"Pendiente\",\"proyectoId\":1}"
                            )
                    )
            )
            @RequestBody ColumnaModel columnaModel) {

        return new ResponseEntity<>(columnaService.guardar(columnaModel), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una columna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Columna actualizada"),
            @ApiResponse(responseCode = "404", description = "Columna o proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ColumnaModel> actualizar(
            @PathVariable Integer id,
            @RequestBody ColumnaModel columnaModel) {

        return ResponseEntity.ok(columnaService.actualizar(id, columnaModel));
    }

    @Operation(summary = "Eliminar una columna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Columna eliminada"),
            @ApiResponse(responseCode = "404", description = "Columna no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        columnaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}