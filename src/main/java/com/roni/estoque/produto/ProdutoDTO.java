package com.roni.estoque.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private double preco;

    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;

}
