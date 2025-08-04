package com.example.trabalho_tcc.repository;

import com.example.trabalho_tcc.dto.RelatorioMovimentacaoDTO;

import java.util.List;

public interface RelatorioRepository {
    List<RelatorioMovimentacaoDTO> buscarRelatorioMovimentacao();
}
