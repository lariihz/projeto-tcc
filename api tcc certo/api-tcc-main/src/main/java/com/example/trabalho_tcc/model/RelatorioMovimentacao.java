package com.example.trabalho_tcc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioMovimentacao {
    private Long pecaId;
    private String pecaNome;
    private Integer totalEntradas;
    private Integer totalSaidas;
    private Integer saldo;
}
