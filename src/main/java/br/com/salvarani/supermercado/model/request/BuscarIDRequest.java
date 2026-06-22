package br.com.salvarani.supermercado.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BuscarIDRequest(@NotBlank
                              Long id) {
}
