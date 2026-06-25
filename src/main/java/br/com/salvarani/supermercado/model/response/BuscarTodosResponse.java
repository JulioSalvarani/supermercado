package br.com.salvarani.supermercado.model.response;

import br.com.salvarani.supermercado.model.Produto;
import lombok.Builder;
import java.util.List;

@Builder

public record BuscarTodosResponse (
                                   List<Produto> listaProdutos
                                   ) {
}
