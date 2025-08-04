package com.example.trabalho_tcc.repository;

import com.example.trabalho_tcc.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PecaRepository extends JpaRepository<Peca, Long> {

    List<Peca> findByAtivoTrue();

    List<Peca> findByQuantidadeLessThanEqualAndAtivoTrue(Integer quantidade);
}
