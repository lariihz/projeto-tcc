package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.ServicoDTO;
import com.example.trabalho_tcc.model.Peca;
import com.example.trabalho_tcc.model.Servico;
import com.example.trabalho_tcc.repository.PecaRepository;
import com.example.trabalho_tcc.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private PecaRepository pecaRepository;

    public Servico salvar(ServicoDTO dto) {
        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setCargo(dto.getCargo());
        servico.setResponsavel(dto.getResponsavel());
        servico.setDataHora(LocalDateTime.now());

        if (dto.getPecaId() != null) {
            Optional<Peca> pecaOptional = pecaRepository.findById(dto.getPecaId());
            pecaOptional.ifPresent(servico::setPeca);
        }

        return servicoRepository.save(servico);
    }

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }
}
