package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.EntradaDTO;
import com.example.trabalho_tcc.model.Entrada;
import com.example.trabalho_tcc.model.Peca;
import com.example.trabalho_tcc.repository.EntradaRepository;
import com.example.trabalho_tcc.repository.PecaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private PecaRepository pecaRepository;

    @Transactional
    public EntradaDTO registrarEntrada(EntradaDTO entradaDTO) {
        Peca peca = pecaRepository.findById(entradaDTO.getPecaId())
                .orElseThrow(() -> new RuntimeException("Peça não encontrada com ID: " + entradaDTO.getPecaId()));

        Entrada entrada = new Entrada();
        entrada.setPeca(peca);
        entrada.setQuantidade(entradaDTO.getQuantidade());
        entrada.setResponsavel(entradaDTO.getResponsavel()); // <--- ADICIONE ESTA LINHA!
        entrada.setDataEntrada(entradaDTO.getDataEntrada());

        Entrada entradaSalva = entradaRepository.save(entrada);
        return converterParaDTO(entradaSalva);
    }

    public List<EntradaDTO> listarEntradas() {
        return entradaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarEntrada(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new RuntimeException("Entrada com ID " + id + " não encontrada para deleção.");
        }
        entradaRepository.deleteById(id);
    }

    // Método auxiliar para converter Entidade para DTO
    private EntradaDTO converterParaDTO(Entrada entrada) {
        EntradaDTO dto = new EntradaDTO();
        dto.setId(entrada.getId());
        dto.setPecaId(entrada.getPeca() != null ? entrada.getPeca().getId() : null);
        dto.setNomePeca(entrada.getPeca() != null ? entrada.getPeca().getNome() : null);
        dto.setQuantidade(entrada.getQuantidade());
        dto.setResponsavel(entrada.getResponsavel()); // <--- ADICIONE ESTA LINHA!
        dto.setDataEntrada(entrada.getDataEntrada());
        return dto;
    }
}