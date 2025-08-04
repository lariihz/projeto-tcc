package com.example.trabalho_tcc.model;

import java.time.LocalDateTime; // Apenas LocalDateTime é necessário

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Lombok annotations para gerar getters, setters e construtores automaticamente.
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Construtor padrão sem argumentos
import lombok.AllArgsConstructor; // Construtor com todos os argumentos

@Entity // Marca a classe como uma entidade JPA
@Table(name = "entradas") // Define o nome da tabela no banco de dados
@Getter // Gera todos os métodos getter para os atributos
@Setter // Gera todos os métodos setter para os atributos
@NoArgsConstructor // Gera um construtor sem argumentos (necessário para JPA)
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class Entrada {

    @Id // Marca o atributo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática do ID pelo banco de dados
    private Long id;

    @ManyToOne // Define um relacionamento muitos para um com a entidade Peca
    @JoinColumn(name = "peca_id", nullable = false) // Mapeia a coluna de chave estrangeira 'peca_id' e a torna obrigatória
    private Peca peca; // A peça associada a esta entrada

    private int quantidade; // Quantidade de peças nesta entrada
       @Column(name = "responsavel") // <--- ESTE CAMPO AQUI!
    private String responsavel;// Nome do responsável pela entrada
    private LocalDateTime dataEntrada; // Data e hora em que a entrada foi registrada
}