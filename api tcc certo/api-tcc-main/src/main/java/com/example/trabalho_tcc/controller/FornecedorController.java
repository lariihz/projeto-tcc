package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.FornecedorDTO; // Supondo que você tenha um DTO para Fornecedor
import com.example.trabalho_tcc.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // Indica que é um controlador REST
@RequestMapping("/api/fornecedores") // Define o caminho base para todos os endpoints deste controlador
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (importante para o frontend)
public class FornecedorController {

    @Autowired // Injeta automaticamente a instância de FornecedorService
    private FornecedorService fornecedorService;

    /**
     * Endpoint para cadastrar um novo fornecedor.
     * Recebe um FornecedorDTO no corpo da requisição.
     * Retorna o FornecedorDTO cadastrado com status 201 Created.
     */
    @PostMapping
    public ResponseEntity<FornecedorDTO> cadastrarFornecedor(@RequestBody FornecedorDTO fornecedorDTO) {
        try {
            FornecedorDTO novoFornecedor = fornecedorService.cadastrarFornecedor(fornecedorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
        } catch (RuntimeException e) {
            // Captura exceções do serviço (ex: CNPJ já existe) e retorna 400 Bad Request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Endpoint para listar todos os fornecedores.
     * Retorna uma lista de FornecedorDTOs com status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarFornecedores() {
        List<FornecedorDTO> fornecedores = fornecedorService.listarFornecedores();
        return ResponseEntity.ok(fornecedores);
    }

    /**
     * Endpoint para buscar um fornecedor pelo ID.
     * Retorna o FornecedorDTO correspondente com status 200 OK, ou 404 Not Found se não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarFornecedorPorId(@PathVariable Long id) {
        try {
            FornecedorDTO fornecedor = fornecedorService.buscarFornecedorPorId(id);
            return ResponseEntity.ok(fornecedor);
        } catch (RuntimeException e) {
            // Captura a exceção se o fornecedor não for encontrado e retorna 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Endpoint para atualizar um fornecedor existente.
     * Recebe o ID do fornecedor na URL e o FornecedorDTO atualizado no corpo da requisição.
     * Retorna o FornecedorDTO atualizado com status 200 OK, ou 404 Not Found se não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorDTO fornecedorDTO) {
        try {
            FornecedorDTO fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedorDTO);
            return ResponseEntity.ok(fornecedorAtualizado);
        } catch (RuntimeException e) {
            // Captura exceções (ex: fornecedor não encontrado, CNPJ duplicado em atualização)
            // Retorna 404 Not Found ou 400 Bad Request dependendo do tipo de erro
            if (e.getMessage().contains("não encontrado")) { // Mensagem de erro do serviço para Fornecedor não encontrado
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    }

    /**
     * Endpoint para deletar um fornecedor pelo ID.
     * Retorna status 204 No Content em caso de sucesso, ou 404 Not Found se não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        try {
            fornecedorService.deletarFornecedor(id);
            // Retorna 204 No Content, indicando que a requisição foi bem-sucedida e não há conteúdo para retornar.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Captura a exceção se o fornecedor não for encontrado e retorna 404 Not Found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}