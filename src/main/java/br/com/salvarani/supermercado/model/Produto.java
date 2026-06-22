package br.com.salvarani.supermercado.model;


import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Produto {
    private Long id;
    private String descricao;
    private String lote;
    private LocalDate dataValidade;
    private LocalDate dataCriacao;

}
