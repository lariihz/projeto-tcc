package com.example.trabalho_tcc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    private String cliente;

    private String status;

    private int quantidade;

    private String observacao;  // <--- campo adicionando

    @ManyToOne
    @JoinColumn(name = "peca_id", nullable = false)
    private Peca peca;

    // outros getters/setters (lombok cuida disso)
}
