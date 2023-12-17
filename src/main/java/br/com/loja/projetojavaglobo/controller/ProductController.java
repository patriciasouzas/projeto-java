package br.com.loja.projetojavaglobo.controller;

import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProductDto;
import br.com.loja.projetojavaglobo.model.dto.ResponseProductDto;
import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/cadastrar")
    public ResponseProductDto createProduct(@RequestBody RequestProductDto requestProductDto){
        Product product = productService.createProduct(requestProductDto);
        return product.toProductDto();
    }

    @GetMapping
    public ResponseEntity<List<Product>> findProdutos() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> findProdutoById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product.toProductDto());
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
