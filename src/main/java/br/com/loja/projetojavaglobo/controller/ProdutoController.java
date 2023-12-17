package br.com.loja.projetojavaglobo.controller;

import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProdutoDto;
import br.com.loja.projetojavaglobo.model.dto.ResponseProdutoDto;
import br.com.loja.projetojavaglobo.model.entities.Produto;
import br.com.loja.projetojavaglobo.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    @Autowired
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar")
    public ResponseProdutoDto createProduto(@RequestBody RequestProdutoDto requestProdutoDto){
        Produto produto = produtoService.createProduto(requestProdutoDto);
        return produto.toProdutoDto();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findProdutos() {
        List<Produto> produtos = produtoService.findProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProdutoDto> findProdutoById(@PathVariable Long id) {
        try {
            Produto produto = produtoService.findById(id);
            return ResponseEntity.ok(produto.toProdutoDto());
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        try {
            produtoService.deleteProduto(id);
            return ResponseEntity.noContent().build();
        }
        catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
