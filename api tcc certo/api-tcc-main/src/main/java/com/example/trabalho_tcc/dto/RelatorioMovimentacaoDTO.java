package com.example.trabalho_tcc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioMovimentacaoDTO {
    private Long pecaId;
    private String pecaNome;
    private Integer totalEntradas;
    private Integer totalSaidas;
    private Integer saldo;
}
