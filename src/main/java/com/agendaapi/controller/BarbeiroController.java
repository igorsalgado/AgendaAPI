package com.agendaapi.controller;


import com.agendaapi.dto.BarbeiroDTO;
import com.agendaapi.service.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/barbeiros")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping
    public List<BarbeiroDTO> findAll() {
        return barbeiroService.findAll();
    }

    @GetMapping("/{id}")
    public BarbeiroDTO findById(@PathVariable Long id) {
        return barbeiroService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarbeiroDTO save(@RequestBody @Valid BarbeiroDTO barbeiroDTO) {
        return barbeiroService.save(barbeiroDTO);
    }

    @PutMapping("/{id}")
    public BarbeiroDTO update(@PathVariable Long id, @RequestBody @Valid BarbeiroDTO barbeiroDTO) {
        return barbeiroService.update(id, barbeiroDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        barbeiroService.delete(id);
    }
}
