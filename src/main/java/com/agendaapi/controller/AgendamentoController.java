package com.agendaapi.controller;

import com.agendaapi.dto.AgendamentoDTO;
import com.agendaapi.service.AgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendamentos")
@Api("API de agendamentos") // Documentação da API
@CrossOrigin(origins = "*") // Permite que qualquer domínio acesse a API
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    @ApiOperation("Lista todos os agendamentos")
    public List<AgendamentoDTO> findAll() {
        return agendamentoService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca um agendamento pelo id")
    public AgendamentoDTO findById(@PathVariable Long id) {
        return agendamentoService.findById(id);
    }

    @GetMapping("/agendados/{data}")
    @ApiOperation("Busca um agendamento pela data")
    public ResponseEntity<List<AgendamentoDTO>> findByData(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<AgendamentoDTO> agendamentoDTOS = agendamentoService.findByData(data);

        return ResponseEntity.ok(agendamentoDTOS);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um novo agendamento")
    public AgendamentoDTO save(@RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.save(agendamentoDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um agendamento")
    public AgendamentoDTO update(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.update(id, agendamentoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Exclui um agendamento")
    public void delete(@PathVariable Long id) {
        agendamentoService.delete(id);
    }
}
