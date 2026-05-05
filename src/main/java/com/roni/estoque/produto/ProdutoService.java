package com.roni.estoque.produto;

import com.roni.estoque.categoria.Categoria;
import com.roni.estoque.categoria.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repository, CategoriaService categoriaService){
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto findOne(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto " + id + " não encontrado"
                ));

    }

    public Produto create(ProdutoDTO dto) {
        //para verificar se a categoria existe
        Categoria categoria = categoriaService.findOne(dto.getCategoriaId());

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(categoria);
        return repository.save(produto);

    }

    public Produto update(Long id, ProdutoDTO dto){
        Produto produto = findOne(id);
        Categoria categoria = categoriaService.findOne(dto.getCategoriaId());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(categoria);
        return repository.save(produto);
    }

    public void remove(Long id){
        findOne(id);
        repository.deleteById(id);
    }



}
