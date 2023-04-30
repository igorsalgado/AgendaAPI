package com.agendaapi.service;

import com.agendaapi.dto.BarbeiroDTO;
import com.agendaapi.model.Barbeiro;
import com.agendaapi.repository.BarbeiroRepository;
import org.modelmapper.ModelMapper;
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

    public BarbeiroDTO save(BarbeiroDTO barbeiroDTO) {
        Barbeiro barbeiro = modelMapper.map(barbeiroDTO, Barbeiro.class);
        barbeiro.setId(null); // Garante que o barbeiro seja salvo como um novo registro
        barbeiro = barbeiroRepository.save(barbeiro);
        return modelMapper.map(barbeiro, BarbeiroDTO.class);
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
