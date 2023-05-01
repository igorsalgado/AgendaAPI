package com.agendaapi.service;

import com.agendaapi.dto.AgendamentoDTO;
import com.agendaapi.model.Agendamento;
import com.agendaapi.model.Barbeiro;
import com.agendaapi.model.Cliente;
import com.agendaapi.repository.AgendamentoRepository;
import com.agendaapi.repository.BarbeiroRepository;
import com.agendaapi.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

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

    public List<AgendamentoDTO> findByData(LocalDate data) {

        List<Agendamento> agendamentos = agendamentoRepository.findAll(); // Busca todos os agendamentos

        List<Agendamento> agendamentosByData = agendamentos.stream()
                .filter(agendamento -> agendamento.getDataAgendamento().toLocalDate().equals(data))
                .collect(Collectors.toList()); // Filtra os agendamentos pela data

        List<AgendamentoDTO> agendamentoDTOS = agendamentosByData.stream()
                .map(agendamento -> modelMapper.map(agendamento, AgendamentoDTO.class))
                .collect(Collectors.toList()); // Converte os agendamentos para DTO

        return agendamentoDTOS;
    }

    public AgendamentoDTO save(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = modelMapper.map(agendamentoDTO, Agendamento.class);
        agendamento.setId(null); // Garante que será criado um novo registro


        Cliente cliente = clienteRepository.findById(agendamentoDTO.getCliente().getId()) // Busca o cliente pelo id
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoDTO.getBarbeiro().getId()) // Busca o barbeiro pelo id
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        agendamento.setCliente(cliente); // Seta o cliente no agendamento
        agendamento.setBarbeiro(barbeiro); // Seta o barbeiro no agendamento);

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return modelMapper.map(agendamentoSalvo, AgendamentoDTO.class);
    }

    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Cliente cliente = clienteRepository.findById(agendamentoDTO.getCliente().getId()) // Busca o cliente pelo id
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoDTO.getBarbeiro().getId()) // Busca o barbeiro pelo id
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        agendamento.setDataAgendamento(agendamentoDTO.getDataAgendamento()); // Seta a data de agendamento

        agendamento.setCliente(modelMapper.map(agendamentoDTO.getCliente(), Cliente.class));
        agendamento.setCliente(cliente); // Seta o cliente no agendamento

        agendamento.setBarbeiro(modelMapper.map(agendamentoDTO.getBarbeiro(), Barbeiro.class));
        agendamento.setBarbeiro(barbeiro); // Seta o barbeiro no agendamento

        Agendamento agendamentoAtualizado = agendamentoRepository.save(agendamento);

        return modelMapper.map(agendamentoAtualizado, AgendamentoDTO.class);
    }

    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}
