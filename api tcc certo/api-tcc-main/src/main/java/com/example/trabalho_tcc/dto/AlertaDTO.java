package com.example.trabalho_tcc.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertaDTO {
    private Long id;
    private String mensagem;
    private String tipo;
    private String destinatario;
    private LocalDateTime dataHora;
    private boolean visualizado;
}
