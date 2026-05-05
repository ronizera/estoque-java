package com.roni.estoque.categoria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;

    }

    @GetMapping
    public List<Categoria> findAll() {
        return  service.findAll();

    }

    @GetMapping("/{id}")
    public Categoria findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@RequestBody @Valid CategoriaDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Long id, @RequestBody @Valid CategoriaDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        service.remove(id);
    }
}
