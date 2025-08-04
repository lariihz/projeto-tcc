package com.example.trabalho_tcc.repository;

import com.example.trabalho_tcc.dto.RelatorioMovimentacaoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelatorioRepositoryImpl implements RelatorioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RelatorioMovimentacaoDTO> buscarRelatorioMovimentacao() {
        String jpql = """
            SELECT new com.example.trabalho_tcc.dto.RelatorioMovimentacaoDTO(
                p.id,
                p.nome,
                COALESCE(SUM(e.quantidade), 0),
                COALESCE(SUM(s.quantidade), 0),
                COALESCE(SUM(e.quantidade), 0) - COALESCE(SUM(s.quantidade), 0)
            )
            FROM Peca p
            LEFT JOIN Entrada e ON e.peca.id = p.id
            LEFT JOIN Saida s ON s.peca.id = p.id
            GROUP BY p.id, p.nome
            ORDER BY p.nome
            """;

        TypedQuery<RelatorioMovimentacaoDTO> query = em.createQuery(jpql, RelatorioMovimentacaoDTO.class);
        return query.getResultList();
    }
}
