package br.com.salvarani.supermercado.exception;

import br.com.salvarani.supermercado.model.Produto;

public class ProdutoException extends RuntimeException {
    public ProdutoException (String message){
        super();
    }
    public ProdutoException (String message, Long id){
        super(message+" erro no produto = "+id);
    }
}
