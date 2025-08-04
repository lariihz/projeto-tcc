package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.SaidaRequest;
import com.example.trabalho_tcc.model.Saida;
import com.example.trabalho_tcc.service.SaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/saidas")
@CrossOrigin(origins = "*") // ajuste conforme necessário
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @PostMapping
    public ResponseEntity<?> registrarSaida(@RequestBody SaidaRequest request) {
        try {/* package com.example.trabalho_tcc.controller;

import com.example.trabalho_tcc.dto.SaidaRequest;
import com.example.trabalho_tcc.model.Saida;
import com.example.trabalho_tcc.service.SaidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saidas")
@CrossOrigin(origins = "*") // ajuste conforme seu front
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @PostMapping
    public ResponseEntity<?> registrarSaida(@RequestBody SaidaRequest request) {
        try {
            Saida saida = saidaService.registrarSaida(
                request.getPecaId(),
                request.getQuantidade(),
                request.getResponsavel()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(saida);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Saida>> listarSaidas() {
        // implementar listagem conforme necessidade
        return ResponseEntity.ok(saidaService.listarTodas());
    }
}
 */
            Saida saida = saidaService.registrarSaida(
                request.getPecaId(),
                request.getQuantidade(),
                request.getResponsavel()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(saida);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Saida>> listarSaidas() {
        List<Saida> saidas = saidaService.listarSaidas();
        return ResponseEntity.ok(saidas);
    }
}
