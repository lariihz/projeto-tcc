package com.example.trabalho_tcc.service;

import com.example.trabalho_tcc.dto.FornecedorDTO;
import com.example.trabalho_tcc.model.Fornecedor; // Supondo que você tenha sua entidade Fornecedor
import com.example.trabalho_tcc.repository.FornecedorRepository; // Supondo que você tenha seu repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional; // Importe para transações, se estiver usando Spring Data JPA

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Indica que é uma classe de serviço Spring
public class FornecedorService {

    @Autowired // Injeta automaticamente a instância de FornecedorRepository
    private FornecedorRepository fornecedorRepository;

    /**
     * Cadastra um novo fornecedor.
     * Realiza validações como CNPJ duplicado.
     * @param fornecedorDTO O DTO com os dados do novo fornecedor.
     * @return O FornecedorDTO do fornecedor cadastrado.
     * @throws RuntimeException se o CNPJ já estiver cadastrado.
     */
    @Transactional // Garante que toda a operação seja executada em uma única transação
    public FornecedorDTO cadastrarFornecedor(FornecedorDTO fornecedorDTO) {
        // Validação: Verificar se já existe um fornecedor com o mesmo CNPJ
        if (fornecedorRepository.findByCnpj(fornecedorDTO.getCnpj()).isPresent()) {
            throw new RuntimeException("CNPJ já cadastrado para outro fornecedor.");
        }

        Fornecedor fornecedor = converterParaEntidade(fornecedorDTO);
        fornecedor = fornecedorRepository.save(fornecedor); // Salva no banco de dados
        return converterParaDTO(fornecedor); // Converte e retorna o DTO
    }

    /**
     * Lista todos os fornecedores.
     * @return Uma lista de FornecedorDTOs.
     */
    public List<FornecedorDTO> listarFornecedores() {
        return fornecedorRepository.findAll().stream()
                .map(this::converterParaDTO) // Converte cada entidade para DTO
                .collect(Collectors.toList());
    }

    /**
     * Busca um fornecedor pelo ID.
     * @param id O ID do fornecedor.
     * @return O FornecedorDTO correspondente.
     * @throws RuntimeException se o fornecedor não for encontrado.
     */
    public FornecedorDTO buscarFornecedorPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));
        return converterParaDTO(fornecedor);
    }

    /**
     * Atualiza um fornecedor existente.
     * @param id O ID do fornecedor a ser atualizado.
     * @param fornecedorDTO O DTO com os dados atualizados do fornecedor.
     * @return O FornecedorDTO atualizado.
     * @throws RuntimeException se o fornecedor não for encontrado ou se o CNPJ já estiver em uso por outro fornecedor.
     */
    @Transactional
    public FornecedorDTO atualizarFornecedor(Long id, FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));

        // Validação: Verificar se o CNPJ já existe para OUTRO fornecedor
        Optional<Fornecedor> fornecedorComCnpj = fornecedorRepository.findByCnpj(fornecedorDTO.getCnpj());
        if (fornecedorComCnpj.isPresent() && !fornecedorComCnpj.get().getId().equals(id)) {
            throw new RuntimeException("CNPJ já cadastrado para outro fornecedor.");
        }

        // Atualiza os campos do fornecedor existente com os dados do DTO
        fornecedorExistente.setNome(fornecedorDTO.getNome());
        fornecedorExistente.setCnpj(fornecedorDTO.getCnpj());
        fornecedorExistente.setEmail(fornecedorDTO.getEmail());
        fornecedorExistente.setEndereco(fornecedorDTO.getEndereco());
        fornecedorExistente.setNumero(fornecedorDTO.getNumero());
        fornecedorExistente.setBairro(fornecedorDTO.getBairro());
        fornecedorExistente.setCidade(fornecedorDTO.getCidade());
        fornecedorExistente.setEstado(fornecedorDTO.getEstado());
        fornecedorExistente.setCep(fornecedorDTO.getCep());
        fornecedorExistente.setTelefone(fornecedorDTO.getTelefone());

        fornecedorExistente = fornecedorRepository.save(fornecedorExistente); // Salva as alterações
        return converterParaDTO(fornecedorExistente);
    }

    /**
     * Deleta um fornecedor pelo ID.
     * @param id O ID do fornecedor a ser deletado.
     * @throws RuntimeException se o fornecedor não for encontrado.
     */
    @Transactional
    public void deletarFornecedor(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RuntimeException("Fornecedor com ID " + id + " não encontrado.");
        }
        fornecedorRepository.deleteById(id);
    }

    // --- Métodos de Conversão (DTO <-> Entidade) ---
    // Você deve ter uma entidade Fornecedor e um DTO FornecedorDTO com os mesmos campos.

    private Fornecedor converterParaEntidade(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        // Não defina o ID aqui, ele será gerado automaticamente no cadastro
        // ou virá do banco para atualização.
        fornecedor.setNome(dto.getNome());
        fornecedor.setCnpj(dto.getCnpj());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setEndereco(dto.getEndereco());
        fornecedor.setNumero(dto.getNumero());
        fornecedor.setBairro(dto.getBairro());
        fornecedor.setCidade(dto.getCidade());
        fornecedor.setEstado(dto.getEstado());
        fornecedor.setCep(dto.getCep());
        fornecedor.setTelefone(dto.getTelefone());
        return fornecedor;
    }

    private FornecedorDTO converterParaDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(fornecedor.getId()); // O ID é importante para o frontend
        dto.setNome(fornecedor.getNome());
        dto.setCnpj(fornecedor.getCnpj());
        dto.setEmail(fornecedor.getEmail());
        dto.setEndereco(fornecedor.getEndereco());
        dto.setNumero(fornecedor.getNumero());
        dto.setBairro(fornecedor.getBairro());
        dto.setCidade(fornecedor.getCidade());
        dto.setEstado(fornecedor.getEstado());
        dto.setCep(fornecedor.getCep());
        dto.setTelefone(fornecedor.getTelefone());
        return dto;
    }
}