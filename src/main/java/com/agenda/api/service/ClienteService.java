package com.agenda.api.service;

import com.agenda.api.dto.ClienteDTO;
import com.agenda.api.model.Cliente;
import com.agenda.api.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(java.util.stream.Collectors.toList());
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> findByNome(String nome) {

        List<Cliente> clientes = clienteRepository.findByNome(nome);
        return modelMapper.map(clientes, new TypeToken<List<ClienteDTO>>() {}.getType()); // TypeToken é necessário para mapear uma lista
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.setId(null); // Garante que o cliente seja salvo como um novo registro

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return modelMapper.map(clienteSalvo, ClienteDTO.class);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDTO.getNome());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return modelMapper.map(clienteAtualizado, ClienteDTO.class);
    }

public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

}
