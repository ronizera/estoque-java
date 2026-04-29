package com.roni.estoque.estoque;


import com.roni.estoque.produto.Produto;
import com.roni.estoque.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstoqueService {

    private final MovimentacaoRepository repository;
    private final ProdutoService produtoService;

    public EstoqueService(MovimentacaoRepository repository, ProdutoService service){
        this.repository = repository;
        this.produtoService = service;
    }

    //aqui vamos calcular o estoque atual do produto
    public Integer calcularEstoque(Long produtoId){
        List<Movimentacao> movimentacoes = repository.findByProdutoId(produtoId);
        return movimentacoes.stream()
                .mapToInt(m -> m.getTipo() == TipoMovimentacao.ENTRADA
                ? m.getQuantidade()
                        : -m.getQuantidade())
                .sum();
    }

    //retorna o estoque atual de todos os produtos

    public List<EstoqueDTO> findAll(){
        return produtoService.findAll().stream()
                .map(p -> new EstoqueDTO(
                        p.getId(),
                        p.getNome(),
                        p.getCategoria().getNome(),
                        calcularEstoque(p.getId())
                ))
                .toList();
    }

    //para retornar o estoque de um produto especifico
    public EstoqueDTO findOne(Long produtoId) {
        Produto produto = produtoService.findOne(produtoId);
        return new EstoqueDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria().getNome(),
                calcularEstoque(produtoId)
        );

    }

    public List<Movimentacao> findHistorico(){
        return repository.findAll();
    }

    public Movimentacao entrada(MovimentacaoDTO dto) {
        Produto produto = produtoService.findOne(dto.getProdutoId());

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipo(TipoMovimentacao.ENTRADA);
        mov.setQuantidade(dto.getQuantidade());
        return repository.save(mov);
    }

    public Movimentacao saida(MovimentacaoDTO dto){
        Produto produto = produtoService.findOne(dto.getProdutoId());

        Integer estoqueAtual = calcularEstoque(dto.getProdutoId());
        if(estoqueAtual < dto.getQuantidade())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estoque insuficiente. Disponível: " + estoqueAtual
            );

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipo(TipoMovimentacao.SAIDA);
        mov.setQuantidade(dto.getQuantidade());
        return repository.save(mov);
    }




}
