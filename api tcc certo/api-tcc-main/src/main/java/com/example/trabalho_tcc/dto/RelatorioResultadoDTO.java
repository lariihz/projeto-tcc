package com.example.trabalho_tcc.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RelatorioResultadoDTO {
    private String tipo;
    private String descricao;
    private String responsavel;
    private LocalDateTime dataHora;
}
