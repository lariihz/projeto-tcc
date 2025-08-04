package com.example.trabalho_tcc.dto; // Garanta que o pacote esteja correto

// Importações necessárias (dependendo do seu projeto, jakarta.validation pode ser útil)
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;

public class FornecedorDTO {

    private Long id; // ID para operações de edição e exclusão

    // @NotBlank(message = "O nome é obrigatório") // Exemplo de validação se estiver usando jakarta.validation
    private String nome;

    // @NotBlank(message = "O CNPJ é obrigatório")
    // @Size(min = 14, max = 18, message = "CNPJ deve ter entre 14 e 18 caracteres")
    private String cnpj;

    // @NotBlank(message = "O e-mail é obrigatório")
    // @Email(message = "E-mail inválido")
    private String email;

    // @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    private String numero; // Pode ser String se aceitar "S/N" ou similares

    private String bairro;

    private String cidade;

    // @NotBlank(message = "O estado é obrigatório")
    // @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres (UF)")
    private String estado;

    private String cep;

    private String telefone;

    // Construtor padrão (geralmente exigido por frameworks como Spring)
    public FornecedorDTO() {
    }

    // --- Getters e Setters para todos os campos ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}