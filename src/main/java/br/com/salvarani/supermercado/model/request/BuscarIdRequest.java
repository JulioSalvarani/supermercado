package br.com.salvarani.supermercado.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder

public record BuscarIdRequest(@NotNull Long id) {
}
