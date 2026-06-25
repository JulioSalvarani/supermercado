package br.com.salvarani.supermercado.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlterarProdutoRequest(
                                    String descricao,
                                    String lote,
                                    LocalDate dataValidade,
                                    LocalDate dataCriacao
) {
}
