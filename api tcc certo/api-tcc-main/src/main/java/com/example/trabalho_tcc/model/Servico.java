package com.example.trabalho_tcc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String cargo;

    private String responsavel;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca; // opcional
}
