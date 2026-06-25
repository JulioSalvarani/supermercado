package br.com.salvarani.supermercado.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
@Builder

public record SalvarProdutoRequest(@NotBlank
                                   String descricao,
                                   @NotBlank
                                   String lote,
                                   @NotNull
                                   LocalDate dataValidade
                                   ) {
}
