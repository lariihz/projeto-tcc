package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.AlertaDTO;
import com.example.trabalho_tcc.model.Alerta;
import com.example.trabalho_tcc.repository.AlertaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public Alerta criarAlerta(AlertaDTO dto) {
        Alerta alerta = new Alerta();
        alerta.setMensagem(dto.getMensagem());
        alerta.setTipo(dto.getTipo());
        alerta.setDestinatario(dto.getDestinatario());
        alerta.setDataHora(LocalDateTime.now());
        alerta.setVisualizado(false);

        return alertaRepository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public List<Alerta> listarNaoVisualizadosPorDestinatario(String destinatario) {
        return alertaRepository.findByDestinatarioAndVisualizadoFalse(destinatario);
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    public void marcarComoVisualizado(Long id) {
        Optional<Alerta> alerta = alertaRepository.findById(id);
        alerta.ifPresent(a -> {
            a.setVisualizado(true);
            alertaRepository.save(a);
        });
    }

    public void deletar(Long id) {
        alertaRepository.deleteById(id);
    }
}
