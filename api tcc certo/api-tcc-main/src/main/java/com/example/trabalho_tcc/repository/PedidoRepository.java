package com.example.trabalho_tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.trabalho_tcc.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
