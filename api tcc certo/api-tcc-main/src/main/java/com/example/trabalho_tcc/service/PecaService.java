package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.PecaDTO;
import com.example.trabalho_tcc.model.Peca;
import com.example.trabalho_tcc.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    public List<PecaDTO> listarTodas() {
        return pecaRepository.findByAtivoTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PecaDTO buscarPorId(Long id) {
        Optional<Peca> peca = pecaRepository.findById(id);
        return peca.filter(Peca::getAtivo).map(this::toDTO).orElse(null);
    }

    public PecaDTO salvar(PecaDTO dto) {
        Peca peca = toEntity(dto);
        return toDTO(pecaRepository.save(peca));
    }

    public void deletar(Long id) {
        Optional<Peca> pecaOpt = pecaRepository.findById(id);
        if (pecaOpt.isPresent()) {
            Peca peca = pecaOpt.get();
            peca.setAtivo(false);
            pecaRepository.save(peca);
        } else {
            throw new IllegalArgumentException("Peça não encontrada para inativação");
        }
    }

    public List<PecaDTO> listarBaixoEstoque() {
        return pecaRepository.findByQuantidadeLessThanEqualAndAtivoTrue(5).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PecaDTO toDTO(Peca peca) {
        return new PecaDTO(
                peca.getId(),
                peca.getNome(),
                peca.getDescricao(),
                peca.getQuantidade(),
                peca.getEstoqueMinimo(),
                peca.getAtivo()
        );
    }

    private Peca toEntity(PecaDTO dto) {
        Peca peca = new Peca();
        peca.setId(dto.getId());
        peca.setNome(dto.getNome());
        peca.setDescricao(dto.getDescricao());
        peca.setQuantidade(dto.getQuantidade());
        peca.setEstoqueMinimo(dto.getEstoqueMinimo());
        peca.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        return peca;
    }

    public PecaDTO atualizar(Long id, PecaDTO dto) {
    try {
        Optional<Peca> pecaOpt = pecaRepository.findById(id);
        if (pecaOpt.isEmpty() || !pecaOpt.get().getAtivo()) {
            throw new IllegalArgumentException("Peça não encontrada ou inativa.");
        }

        Peca peca = pecaOpt.get();
        peca.setNome(dto.getNome());
        peca.setDescricao(dto.getDescricao());
        peca.setQuantidade(dto.getQuantidade());
        peca.setEstoqueMinimo(dto.getEstoqueMinimo());

        if (dto.getAtivo() != null) {
            peca.setAtivo(dto.getAtivo());
        }

        return toDTO(pecaRepository.save(peca));
    } catch (Exception e) {
        // aqui você pode usar um logger, por exemplo:
        System.err.println("Erro ao atualizar peça: " + e.getMessage());
        throw e; // re-throw para controller tratar
    }
}


}
