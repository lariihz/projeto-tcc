package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.RelatorioMovimentacaoDTO;
import com.example.trabalho_tcc.repository.EntradaRepository;
import com.example.trabalho_tcc.repository.SaidaRepository;
import com.example.trabalho_tcc.repository.PecaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private PecaRepository pecaRepository;

    public List<RelatorioMovimentacaoDTO> gerarRelatorioMovimentacao() {
        // Buscando todas as peças cadastradas
        var pecas = pecaRepository.findAll();

        // Map para juntar entradas e saídas por peça
        Map<Long, Integer> totalEntradasPorPeca = new HashMap<>();
        Map<Long, Integer> totalSaidasPorPeca = new HashMap<>();

        // Calcular totais de entradas agrupados por peça
        entradaRepository.findAll().forEach(entrada -> {
            Long pecaId = entrada.getPeca().getId();
            totalEntradasPorPeca.put(pecaId, totalEntradasPorPeca.getOrDefault(pecaId, 0) + entrada.getQuantidade());
        });

        // Calcular totais de saídas agrupados por peça
        saidaRepository.findAll().forEach(saida -> {
            Long pecaId = saida.getPeca().getId();
            totalSaidasPorPeca.put(pecaId, totalSaidasPorPeca.getOrDefault(pecaId, 0) + saida.getQuantidade());
        });

        // Montar lista final DTO
        List<RelatorioMovimentacaoDTO> relatorio = pecas.stream()
            .map(peca -> {
                int entradas = totalEntradasPorPeca.getOrDefault(peca.getId(), 0);
                int saidas = totalSaidasPorPeca.getOrDefault(peca.getId(), 0);
                int saldo = entradas - saidas;
                return new RelatorioMovimentacaoDTO(
                    peca.getId(),
                    peca.getNome(),
                    entradas,
                    saidas,
                    saldo
                );
            }).collect(Collectors.toList());

        return relatorio;
    }
}
