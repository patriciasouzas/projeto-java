package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProductDto;
import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(RequestProductDto requestProductDto){
        var product = new Product();
        product.setName(requestProductDto.getName());
        product.setDescription(requestProductDto.getDescription());
        product.setPrice(requestProductDto.getPrice());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    public List<Product> findAllProducts() {
        List<Product> productList = productRepository.findAll();

        if (productList.isEmpty()) throw new ProductNotFoundException("Não foram encontrados produtos cadastrados em nossa base de dados.");

        return productList;
    }

    public Product findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Produto não encontrado com Id: \" + id");
        }
        return productOptional.get();
    }

   public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado com Id: " + id);
        }
        productRepository.deleteById(id);
    }
}
