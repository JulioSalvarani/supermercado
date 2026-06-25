package br.com.salvarani.supermercado.model.response;

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
