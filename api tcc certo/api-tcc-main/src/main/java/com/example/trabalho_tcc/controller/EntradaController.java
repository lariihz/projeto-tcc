package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.EntradaDTO;
import com.example.trabalho_tcc.service.EntradaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "*")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    /**
     * Endpoint para registrar uma nova entrada de peça.
     *
     * @param entradaDTO Os dados da entrada enviados no corpo da requisição JSON.
     * @return ResponseEntity<EntradaDTO> Retorna o DTO da entrada registrada com status 201 Created em caso de sucesso,
     * ou 404 Not Found se a peça não for encontrada.
     */
    @PostMapping
    public ResponseEntity<EntradaDTO> registrarEntrada(@RequestBody EntradaDTO entradaDTO) {
        try {
            EntradaDTO entradaRegistrada = entradaService.registrarEntrada(entradaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(entradaRegistrada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Endpoint para listar todas as entradas registradas.
     *
     * @return ResponseEntity<List<EntradaDTO>> Retorna uma lista de DTOs de entradas com status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<EntradaDTO>> listarEntradas() {
        List<EntradaDTO> entradas = entradaService.listarEntradas();
        return ResponseEntity.ok(entradas);
    }

    /**
     * Endpoint para deletar uma entrada pelo seu ID.
     *
     * @param id O ID da entrada a ser deletada, passado como variável de caminho na URL.
     * @return ResponseEntity<Void> Retorna status 204 No Content em caso de sucesso (entrada deletada),
     * ou 404 Not Found se a entrada não for encontrada.
     */
    @DeleteMapping("/{id}") // A anotação @RequestMapping na classe já define '/api/entradas', então só precisamos de '/{id}' aqui.
    public ResponseEntity<Void> deletarEntrada(@PathVariable Long id) {
        try {
            // Delega a lógica de deleção e verificação de existência para o serviço.
            entradaService.deletarEntrada(id);
            // Se o serviço não lançar exceção, significa que a entrada foi deletada com sucesso.
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (RuntimeException e) {
            // Se o serviço lançar uma RuntimeException (por exemplo, "Entrada não encontrada"),
            // capturamos e lançamos uma ResponseStatusException para retornar um 404 Not Found.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}