package com.example.trabalho_tcc.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private String cliente;
    private String status;
    private Long pecaId;
    private int quantidade;
    private String observacao;  // <--- campo adicionado
}
