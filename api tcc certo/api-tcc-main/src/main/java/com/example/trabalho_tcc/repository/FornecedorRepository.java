package com.example.trabalho_tcc.repository;

import com.example.trabalho_tcc.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    // Método para buscar fornecedor por CNPJ, usado para validação
    Optional<Fornecedor> findByCnpj(String cnpj);
}