package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.AlertaDTO;
import com.example.trabalho_tcc.model.Alerta;
import com.example.trabalho_tcc.service.AlertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping
    public Alerta criarAlerta(@RequestBody AlertaDTO dto) {
        return alertaService.criarAlerta(dto);
    }

    @GetMapping
    public List<Alerta> listarTodos() {
        return alertaService.listarTodos();
    }

    @GetMapping("/nao-visualizados/{destinatario}")
    public List<Alerta> naoVisualizados(@PathVariable String destinatario) {
        return alertaService.listarNaoVisualizadosPorDestinatario(destinatario);
    }

    @PutMapping("/{id}/visualizado")
    public void marcarComoVisualizado(@PathVariable Long id) {
        alertaService.marcarComoVisualizado(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alertaService.deletar(id);
    }
}
