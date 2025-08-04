package com.example.trabalho_tcc.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ServicoDTO {
    private Long id;
    private String descricao;
    private String cargo;
    private String responsavel;
    private Long pecaId; // opcional
    private LocalDateTime dataHora; // pode ignorar no POST, o sistema seta
}
