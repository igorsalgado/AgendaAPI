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

        //verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(agendamentoDTO.getCliente().getId()) // Busca o cliente pelo id
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //verifica se o barbeiro existe
        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoDTO.getBarbeiro().getId()) // Busca o barbeiro pelo id
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        //Arredonda a hora do agendamento de 30 em 30 minutos
        agendamentoDTO.setDataAgendamento(agendamentoDTO.getDataAgendamento()
                .withMinute(30 * ((agendamentoDTO.getDataAgendamento()
                        .getMinute() + 15) / 30)));

        //Cria o objeto agendamento apartir do DTO
        Agendamento agendamento = modelMapper.map(agendamentoDTO, Agendamento.class);
        agendamento.setId(null); // Garante que será criado um novo registro
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

        //Arredonda a hora do agendamento de 30 em 30 minutos
        agendamentoDTO.setDataAgendamento(agendamentoDTO.getDataAgendamento()
                .withMinute(30 * ((agendamentoDTO.getDataAgendamento()
                        .getMinute() + 15) / 30)));

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
