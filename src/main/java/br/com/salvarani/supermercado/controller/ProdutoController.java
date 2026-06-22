package br.com.salvarani.supermercado.controller;

import br.com.salvarani.supermercado.model.Produto;
import br.com.salvarani.supermercado.model.request.BuscarIDRequest;
import br.com.salvarani.supermercado.model.request.SalvarProdutoRequest;
import br.com.salvarani.supermercado.model.response.BuscarTodosResponse;
import br.com.salvarani.supermercado.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> salvarProduto(@RequestBody SalvarProdutoRequest produto) {
        try {
            produtoService.salvarProduto(produto);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public BuscarTodosResponse buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            BuscarIDRequest request = new BuscarIDRequest(id);
            Produto produto = produtoService.buscarPorId(request);

            if (produto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            log.error("Erro ao buscar produto: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
