package com.agendaapi.service;

import com.agendaapi.dto.AgendamentoDTO;
import com.agendaapi.model.Agendamento;
import com.agendaapi.model.Barbeiro;
import com.agendaapi.model.Cliente;
import com.agendaapi.repository.AgendamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream()
                .map(agendamento -> modelMapper.map(agendamento, AgendamentoDTO.class))
                .collect(Collectors.toList());
    }

    public AgendamentoDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return modelMapper.map(agendamento, AgendamentoDTO.class);
    }

    public AgendamentoDTO save(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = modelMapper.map(agendamentoDTO, Agendamento.class);
        agendamento.setId(null);

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return modelMapper.map(agendamentoSalvo, AgendamentoDTO.class);
    }

    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamento.setDataAgendamento(agendamentoDTO.getDataAgendamento());
        agendamento.setCliente(modelMapper.map(agendamentoDTO.getCliente(), Cliente.class));
        agendamento.setBarbeiro(modelMapper.map(agendamentoDTO.getBarbeiro(), Barbeiro.class));

        Agendamento agendamentoAtualizado = agendamentoRepository.save(agendamento);
        return modelMapper.map(agendamentoAtualizado, AgendamentoDTO.class);
    }

    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}