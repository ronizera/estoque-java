package com.roni.estoque.estoque;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstoqueDTO {
    private Long produtoId;
    private String nome;
    private String categoria;
    private Integer quantidade;
}

