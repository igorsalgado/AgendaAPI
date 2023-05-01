package com.agendaapi.controller;


import com.agendaapi.dto.BarbeiroDTO;
import com.agendaapi.service.BarbeiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/barbeiros")
@Api("API REST barbeiros")
@CrossOrigin(origins = "*")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping
    @ApiOperation("Retorna uma lista de barbeiros")
    public List<BarbeiroDTO> findAll() {
        return barbeiroService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um barbeiro pelo ID")
    public BarbeiroDTO findById(@PathVariable Long id) {
        return barbeiroService.findById(id);
    }

    @GetMapping("/cadastrados/{nome}")
    @ApiOperation("Retorna um barbeiro pelo nome")
    public ResponseEntity<List<BarbeiroDTO>> findByNome(@PathVariable String nome) {
        List<BarbeiroDTO> barbeiroDTOS = barbeiroService.findByNome(nome);
        return ResponseEntity.ok(barbeiroDTOS);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo barbeiro")
    public BarbeiroDTO save(@RequestBody @Valid BarbeiroDTO barbeiroDTO) {
        return barbeiroService.save(barbeiroDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um barbeiro pelo ID")
    public BarbeiroDTO update(@PathVariable Long id, @RequestBody @Valid BarbeiroDTO barbeiroDTO) {
        return barbeiroService.update(id, barbeiroDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta um barbeiro pelo ID")
    public void delete(@PathVariable Long id) {
        barbeiroService.delete(id);
    }
}
