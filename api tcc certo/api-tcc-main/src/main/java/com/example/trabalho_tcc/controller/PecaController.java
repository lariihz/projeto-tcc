package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.PecaDTO;
import com.example.trabalho_tcc.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public List<PecaDTO> listarTodas() {
        return pecaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaDTO> buscarPorId(@PathVariable Long id) {
        PecaDTO dto = pecaService.buscarPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PecaDTO> salvar(@RequestBody PecaDTO dto) {
        PecaDTO novaPeca = pecaService.salvar(dto);
        return ResponseEntity.ok(novaPeca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            pecaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/baixo-estoque")
    public List<PecaDTO> listarBaixoEstoque() {
        return pecaService.listarBaixoEstoque();
    }

    @PutMapping("/{id}")
public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PecaDTO dto) {
    try {
        PecaDTO atualizado = pecaService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}

}
