package com.example.trabalho_tcc.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioLoginDTO {

    private String nome;
    private String email;
    private String cargo;

    public UsuarioLoginDTO(String nome, String email, String cargo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public UsuarioLoginDTO() {
        // Construtor padrão necessário para frameworks como Jackson
    }
}

