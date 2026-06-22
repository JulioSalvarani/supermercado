package br.com.salvarani.supermercado.service;

import br.com.salvarani.supermercado.exception.ProdutoException;
import br.com.salvarani.supermercado.model.Produto;
import br.com.salvarani.supermercado.model.request.BuscarIDRequest;
import br.com.salvarani.supermercado.model.request.SalvarProdutoRequest;
import br.com.salvarani.supermercado.model.response.BuscarTodosResponse;
import br.com.salvarani.supermercado.model.response.SalvarProdutoResponse;
import br.com.salvarani.supermercado.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public SalvarProdutoResponse salvarProduto(SalvarProdutoRequest request) throws Exception {
        try {
            Produto produto = Produto.builder()
                    .lote(request.lote())
                    .dataValidade(request.dataValidade())
                    .dataCriacao(LocalDate.now())
                    .descricao(request.descricao())
                    .build();
            produto = produtoRepository.salvarProduto(produto);
            return SalvarProdutoResponse.builder()
                    .id(produto.getId())
                    .lote(produto.getLote())
                    .dataValidade(produto.getDataValidade())
                    .build();

        } catch (ProdutoException e) {
            log.warn(e.getMessage());
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
        return null;
    }

    public BuscarTodosResponse buscarTodos() {
        try {
            List<Produto> produtoList = produtoRepository.buscartodos();
            return BuscarTodosResponse.builder()
                    .listaProdutos(produtoList)
                    .build();
        } catch (ProdutoException e) {
            log.warn(e.getMessage());
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
        return null;
    }

    public Produto buscarPorId(BuscarIDRequest request) throws Exception {
        try {
            Produto produto = produtoRepository.buscarPorId(request.id());
            return produto;

        } catch (ProdutoException e) {
            log.warn(e.getMessage());
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
        return null;
    }
}


