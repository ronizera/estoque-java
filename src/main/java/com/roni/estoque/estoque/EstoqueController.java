package com.roni.estoque.estoque;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service){
        this.service = service;
    }

    @GetMapping
    public List<EstoqueDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/historico")
    public List<Movimentacao> findHistorico(){
        return service.findHistorico();
    }

    @GetMapping("/{produtoId}")
    public EstoqueDTO findOne(@PathVariable Long produtoId){
        return service.findOne(produtoId);
    }

    @PostMapping("/entrada")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimentacao entrada(@RequestBody @Valid MovimentacaoDTO dto){
        return service.entrada(dto);
    }

    @PostMapping("/saida")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimentacao saida(@RequestBody @Valid MovimentacaoDTO dto){
        return service. saida(dto);
    }
}
