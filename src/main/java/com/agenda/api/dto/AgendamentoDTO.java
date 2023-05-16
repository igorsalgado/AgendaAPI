package com.agenda.api.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AgendamentoDTO {

    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private LocalDateTime dataAgendamento;
    private ClienteDTO cliente;
    private BarbeiroDTO barbeiro;
}
