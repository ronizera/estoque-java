package com.roni.estoque.produto;

import com.roni.estoque.categoria.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private ProdutoService service;

    @Test
    void deveRetornarTodosOsProdutos() {

        Produto produto = new Produto();
        produto.setNome("Arroz");

        when(repository.findAll())
                .thenReturn(List.of(produto));

        List<Produto> produtos = service.findAll();

        assertEquals(1, produtos.size());
        assertEquals("Arroz", produtos.get(0).getNome());

        verify(repository).findAll();
    }

    @Test
    void deveRetornarProdutoPorId() {

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Arroz");

        when(repository.findById(1L))
                .thenReturn(Optional.of(produto));

        Produto resultado = service.findOne(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Arroz", resultado.getNome());

        verify(repository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> service.findOne(1L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Produto 1 não encontrado", exception.getReason());

        verify(repository).findById(1L);
    }
}