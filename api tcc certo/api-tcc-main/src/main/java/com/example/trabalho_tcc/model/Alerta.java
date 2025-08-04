package com.example.trabalho_tcc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    private String tipo; // INFO, ERRO, AVISO, SUCESSO

    private String destinatario; // pode ser "Gerente", "Almoxarife", etc

    private LocalDateTime dataHora;

    private boolean visualizado;
}
