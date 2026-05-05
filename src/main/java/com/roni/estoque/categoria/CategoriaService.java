package com.roni.estoque.categoria;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Categoria findOne(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "categoria" + id + "não encontrada"
                ));
    }

    public Categoria create(CategoriaDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return repository.save(categoria);
    }

    public Categoria update(Long id, @Valid CategoriaDTO dto){
        Categoria categoria = findOne(id);
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return repository.save(categoria);
    }

    public void remove(Long id){
        findOne(id);
        repository.deleteById(id);
    }
}
