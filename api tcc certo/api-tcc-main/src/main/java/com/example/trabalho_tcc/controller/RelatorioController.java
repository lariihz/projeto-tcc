package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.RelatorioMovimentacaoDTO;
import com.example.trabalho_tcc.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/movimentacao")
    public List<RelatorioMovimentacaoDTO> obterRelatorioMovimentacao() {
        return relatorioService.gerarRelatorioMovimentacao();
    }
}
