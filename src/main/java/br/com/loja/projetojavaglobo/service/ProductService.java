package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProductDto;
import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Product createProduct(RequestProductDto requestProductDto){
        var product = new Product();
        product.setName(requestProductDto.getName());
        product.setDescription(requestProductDto.getDescription());
        product.setPrice(requestProductDto.getPrice());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com Id: " + id));
    }

   public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado com Id: " + id);
        }
        productRepository.deleteById(id);
    }
}
