package com.agenda.api.controller;

import com.agenda.api.service.ClienteService;
import com.agenda.api.dto.ClienteDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("API REST clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ApiOperation("Retorna uma lista de clientes")
    public List<ClienteDTO> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um cliente pelo ID")
    public ClienteDTO findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping("/cadastrados/{nome}")
    @ApiOperation("Retorna um cliente pelo nome")
    public ResponseEntity<List<ClienteDTO>> findByNome(@PathVariable String nome) {
        List<ClienteDTO> clienteDTOS = clienteService.findByNome(nome);

        return ResponseEntity.ok(clienteDTOS);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo cliente")
    public ClienteDTO save(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.save(clienteDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um cliente pelo ID")
    public ClienteDTO update(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.update(id, clienteDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta um cliente pelo ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
}
