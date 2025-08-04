package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.ServicoDTO;
import com.example.trabalho_tcc.model.Servico;
import com.example.trabalho_tcc.service.ServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public Servico registrar(@RequestBody ServicoDTO dto) {
        return servicoService.salvar(dto);
    }

    @GetMapping
    public List<Servico> listar() {
        return servicoService.listar();
    }

    @GetMapping("/{id}")
    public Servico buscarPorId(@PathVariable Long id) {
        return servicoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servicoService.deletar(id);
    }
}
