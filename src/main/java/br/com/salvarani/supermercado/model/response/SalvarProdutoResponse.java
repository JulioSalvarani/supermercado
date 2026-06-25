package br.com.salvarani.supermercado.model.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder

public record SalvarProdutoResponse(Long id,
                                    String descricao,
                                    String lote,
                                    LocalDate dataValidade) {
}
