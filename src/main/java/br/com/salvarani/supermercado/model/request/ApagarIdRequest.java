package br.com.salvarani.supermercado.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder

public record ApagarIdRequest(@NotNull Long id) {
}
