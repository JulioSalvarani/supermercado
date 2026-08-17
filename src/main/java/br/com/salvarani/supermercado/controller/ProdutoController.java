package br.com.salvarani.supermercado.controller;


import br.com.salvarani.supermercado.model.request.AlterarProdutoRequest;
import br.com.salvarani.supermercado.model.request.ApagarIdRequest;
import br.com.salvarani.supermercado.model.request.BuscarIdRequest;
import br.com.salvarani.supermercado.model.request.SalvarProdutoRequest;
import br.com.salvarani.supermercado.model.response.AlterarProdutoResponse;
import br.com.salvarani.supermercado.model.response.ApagarIdResponse;
import br.com.salvarani.supermercado.model.response.BuscarIdResponse;
import br.com.salvarani.supermercado.model.response.BuscarTodosResponse;
import br.com.salvarani.supermercado.service.ProdutoService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/produto")
@EnableEncryptableProperties
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> salvarProduto(@RequestBody SalvarProdutoRequest request) {
        try {
            produtoService.salvarProduto(request);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public BuscarTodosResponse buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")

    public ResponseEntity<BuscarIdResponse> buscarPorId(@ModelAttribute @Valid BuscarIdRequest request) {
        try {
            BuscarIdResponse response = produtoService.buscarPorId(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping ("/apagar/{id}")
    public ResponseEntity<Object> apagarId(@ModelAttribute ApagarIdRequest request) {
        try {
            ApagarIdResponse response = produtoService.apagaID(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar/{path}")
    public ResponseEntity<AlterarProdutoResponse> alterarProduto(
            @PathVariable Long id,
            @RequestBody AlterarProdutoRequest request) {
        try{
            AlterarProdutoResponse response = produtoService.alteraProduto(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}