package com.agendaapi.controller;

import com.agendaapi.dto.AgendamentoDTO;
import com.agendaapi.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDTO> findAll() {
        return agendamentoService.findAll();
    }

    @GetMapping("/{id}")
    public AgendamentoDTO findById(@PathVariable Long id) {
        return agendamentoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDTO save(@RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.save(agendamentoDTO);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO update(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.update(id, agendamentoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        agendamentoService.delete(id);
    }
}
