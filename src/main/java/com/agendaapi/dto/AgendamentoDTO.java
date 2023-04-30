package com.agendaapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoDTO {

    private Long id;
    private LocalDateTime dataAgendamento;
    private ClienteDTO cliente;
    private BarbeiroDTO barbeiro;
}
