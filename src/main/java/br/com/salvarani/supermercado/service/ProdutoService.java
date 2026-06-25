package br.com.salvarani.supermercado.service;

import br.com.salvarani.supermercado.exception.ProdutoException;
import br.com.salvarani.supermercado.model.Produto;
import br.com.salvarani.supermercado.model.request.AlterarProdutoRequest;
import br.com.salvarani.supermercado.model.request.ApagarIdRequest;
import br.com.salvarani.supermercado.model.request.BuscarIdRequest;
import br.com.salvarani.supermercado.model.request.SalvarProdutoRequest;
import br.com.salvarani.supermercado.model.response.*;
import br.com.salvarani.supermercado.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                    .descricao(request.descricao())
                    .lote(request.lote())
                    .dataValidade(request.dataValidade())
                    .dataCriacao(LocalDate.now())
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

    public BuscarIdResponse buscarPorId(BuscarIdRequest request) throws Exception {
        try {
            //aqui fazer o build de produto é nada a ver, pq tem um "atributo" só
            Produto produto = produtoRepository.buscarPorId(request.id());
            return BuscarIdResponse.builder()
                    .id(produto.getId())
                    .descricao(produto.getDescricao())
                    .lote(produto.getLote())
                    .dataValidade(produto.getDataValidade())
                    .dataCriacao(produto.getDataCriacao())
                    .build();

        } catch (ProdutoException e) {
            log.warn(e.getMessage());
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
        return null;
    }

    public ApagarIdResponse apagaID(ApagarIdRequest request) throws Exception{
        try {
            Produto produto = produtoRepository.apagaId(request.id());
            return ApagarIdResponse.builder()
                    .id(produto.getId())
                    .descricao(produto.getDescricao())
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

    public AlterarProdutoResponse alteraProduto (Long path, AlterarProdutoRequest request) throws Exception {
        try {
            Produto produtoAlteracoes = Produto.builder()
                    .descricao(request.descricao())
                    .lote(request.lote())
                    .dataValidade(request.dataValidade())
                    .dataCriacao(request.dataCriacao())
                    .build();

            Produto produtoAtualizado = produtoRepository.alterarPorId(path, produtoAlteracoes);

            return AlterarProdutoResponse.builder()
                    .id(produtoAtualizado.getId())
                    .descricao(produtoAtualizado.getDescricao())
                    .lote(produtoAtualizado.getLote())
                    .dataValidade(produtoAtualizado.getDataValidade())
                    .dataCriacao(produtoAtualizado.getDataCriacao())
                    .build();

        } catch (ProdutoException e) {
            log.warn(e.getMessage());
        } catch (Exception exception) {
            log.warn(exception.getMessage());
        }
        return null;
    }
}


