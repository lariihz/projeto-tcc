package com.example.trabalho_tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.trabalho_tcc.model.Alerta;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByDestinatarioAndVisualizadoFalse(String destinatario);
}
