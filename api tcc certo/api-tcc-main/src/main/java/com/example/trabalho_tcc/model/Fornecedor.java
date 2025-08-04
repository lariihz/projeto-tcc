package com.example.trabalho_tcc.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private String email;

    private String endereco;

    private String bairro;

    private String numero;

    private String cidade;

    private String estado;

    private String cep;

    private String telefone;

}
