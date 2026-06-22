package br.com.salvarani.supermercado.model.response;

import br.com.salvarani.supermercado.model.Produto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record BuscarTodosResponse (
                                   List<Produto> listaProdutos
                                   ) {
}
