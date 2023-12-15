package br.com.loja.projetojavaglobo.controller;

import br.com.loja.projetojavaglobo.model.dto.RequestProdutoDto;
import br.com.loja.projetojavaglobo.model.dto.ResponseProdutoDto;
import br.com.loja.projetojavaglobo.model.entities.Produto;
import br.com.loja.projetojavaglobo.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    @PostMapping("/cadastrar")
    public ResponseProdutoDto createProduto(@RequestBody RequestProdutoDto requestProdutoDto){
        Produto produto = produtoService.createProduto(requestProdutoDto);
        return produto.toProdutoDto();
    }

    @GetMapping
    public List<Produto> findProdutos() throws Exception {
        return produtoService.findProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProdutoDto> findProdutoById(Long id) throws Exception {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto.toProdutoDto());
    }
}
