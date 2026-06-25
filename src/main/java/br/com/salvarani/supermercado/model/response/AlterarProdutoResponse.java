package br.com.salvarani.supermercado.model.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder

public record AlterarProdutoResponse(Long id,
                                     String descricao,
                                     String lote,
                                     LocalDate dataValidade,
                                     LocalDate dataCriacao
) {
}
