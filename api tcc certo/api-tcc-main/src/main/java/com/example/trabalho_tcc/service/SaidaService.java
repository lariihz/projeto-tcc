package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.model.Peca;
import com.example.trabalho_tcc.model.Saida;
import com.example.trabalho_tcc.repository.PecaRepository;
import com.example.trabalho_tcc.repository.SaidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaidaService {

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    public Saida registrarSaida(Long pecaId, Integer quantidade, String responsavel) {
        Peca peca = pecaRepository.findById(pecaId)
            .orElseThrow(() -> new RuntimeException("Peça não encontrada"));

        Integer qtdAtual = peca.getQuantidade() == null ? 0 : peca.getQuantidade();

        if (quantidade > qtdAtual) {
            throw new RuntimeException("Estoque insuficiente");
        }

        peca.setQuantidade(qtdAtual - quantidade);
        pecaRepository.save(peca);

        Saida saida = new Saida();
        saida.setPeca(peca);
        saida.setQuantidade(quantidade);
        saida.setResponsavel(responsavel);
        saida.setDataHora(LocalDateTime.now());

        return saidaRepository.save(saida);
    }

    public List<Saida> listarTodas() {
        return saidaRepository.findAll();
    }

    public List<Saida> listarSaidas() {
        // Implementar lógica de listagem de saídas se necessário
        return saidaRepository.findAll();
    }
}
