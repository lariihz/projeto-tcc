package com.example.trabalho_tcc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
private String nome;

@Email(message = "Email inválido")
@NotBlank(message = "Email é obrigatório")
private String email;

@NotBlank(message = "Senha é obrigatória")
private String senha;

@Enumerated(EnumType.STRING)
    @NotNull(message = "Cargo é obrigatório")
    private Cargo cargo;


    // Getters e Setters
}
