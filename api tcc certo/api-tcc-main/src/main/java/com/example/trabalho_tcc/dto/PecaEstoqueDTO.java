package com.example.trabalho_tcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PecaEstoqueDTO {
    private Long id;
    private String nome;
    private int quantidadeAtual;
}
