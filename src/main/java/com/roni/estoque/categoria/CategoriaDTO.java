package com.roni.estoque.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2 , max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotBlank(message = "Descricao é obrigatoria")
    @Size(min = 2, max = 500, message = "Descrição deve ter entre 5 e 200 caracteres")
    private String descricao;
}
