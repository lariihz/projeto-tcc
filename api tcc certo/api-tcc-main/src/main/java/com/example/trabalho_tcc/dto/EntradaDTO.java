package com.example.trabalho_tcc.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntradaDTO {
    private Long pecaId;
    private int quantidade;
    private String responsavel;
    private String nomePeca;
    private Long id;

    // --- MUDANÇA AQUI ---
    // Inclui milissegundos (.SSS) e o 'Z' para o fuso horário UTC (Zulu time)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dataEntrada;

    // ... (restante do seu código)
}