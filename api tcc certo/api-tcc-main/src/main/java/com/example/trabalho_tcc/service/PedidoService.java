package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.model.Pedido;
import com.example.trabalho_tcc.model.Peca;
import com.example.trabalho_tcc.repository.PedidoRepository;
import com.example.trabalho_tcc.repository.PecaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PecaRepository pecaRepository;

    public Pedido salvar(Pedido pedido2) {
        Long pecaId = pedido2.getPeca() != null ? pedido2.getPeca().getId() : null;
        if (pecaId == null) {
            throw new RuntimeException("ID da peça não informado");
        }

        Peca peca = pecaRepository.findById(pecaId)
            .orElseThrow(() -> new RuntimeException("Peça não encontrada"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(pedido2.getDataPedido() != null ? pedido2.getDataPedido() : LocalDateTime.now());
        pedido.setCliente(pedido2.getCliente());
        pedido.setStatus(pedido2.getStatus());
        pedido.setQuantidade(pedido2.getQuantidade());
        pedido.setObservacao(pedido2.getObservacao());  // Aqui setamos a observação
        pedido.setPeca(peca);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
