package com.agenda.api.service;

import com.agenda.api.repository.BarbeiroRepository;
import com.agenda.api.dto.BarbeiroDTO;
import com.agenda.api.model.Barbeiro;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BarbeiroDTO> findAll() {
        List<Barbeiro> barbeiros = barbeiroRepository.findAll();
        return barbeiros.stream()
                .map(barbeiro -> modelMapper.map(barbeiro, BarbeiroDTO.class))
                .collect(java.util.stream.Collectors.toList());
    }

    public BarbeiroDTO findById(Long id) {
        Barbeiro barbeiro = barbeiroRepository.findById(id).orElse(null);
        return modelMapper.map(barbeiro, BarbeiroDTO.class);
    }

    public List<BarbeiroDTO> findByNome(String nome) {
        List<Barbeiro> barbeiros = barbeiroRepository.findByNome(nome);
        return modelMapper.map(barbeiros, new TypeToken<List<BarbeiroDTO>>() {}.getType()); // TypeToken é necessário para mapear uma lista
    }

    public BarbeiroDTO save(BarbeiroDTO barbeiroDTO) {
        Barbeiro barbeiro = modelMapper.map(barbeiroDTO, Barbeiro.class);
        barbeiro.setId(null); // Garante que o barbeiro seja salvo como um novo registro

        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiro);
        return modelMapper.map(barbeiroSalvo, BarbeiroDTO.class);
    }

    public BarbeiroDTO update(Long id, BarbeiroDTO barbeiroDTO) {
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
        barbeiro.setNome(barbeiroDTO.getNome());

        Barbeiro barbeiroAtualizado = barbeiroRepository.save(barbeiro);
        return modelMapper.map(barbeiroAtualizado, BarbeiroDTO.class);
    }

    public void delete(Long id) {
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
        barbeiroRepository.delete(barbeiro);
    }
}
