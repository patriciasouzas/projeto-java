package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.exceptions.DescriptionLengthException;
import br.com.loja.projetojavaglobo.exceptions.InvalidValueException;
import br.com.loja.projetojavaglobo.exceptions.NameLengthException;
import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProductDto;
import br.com.loja.projetojavaglobo.model.dto.RequestUpdateProductDto;
import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(RequestProductDto requestProductDto){
        var product = new Product();

        this.validateDescriptionLength(requestProductDto.getDescription());
        this.validateNameLength(requestProductDto.getName());
        this.validatePrice(requestProductDto.getPrice());

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
            throw new ProductNotFoundException("Produto não encontrado com Id: " + id);
        }
        return productOptional.get();
    }

    public Product updateProduct(Long id, RequestUpdateProductDto updateProduct){
        Product existingProduct = findProductById(id);

        if (existingProduct != null){
            if (updateProduct.getName() != null){
                existingProduct.setName(updateProduct.getName());
            } else if (updateProduct.getDescription() != null){
                existingProduct.setDescription(updateProduct.getDescription());
            } else if (updateProduct.getPrice() != null){
                existingProduct.setPrice(updateProduct.getPrice());
            }

            return productRepository.save(existingProduct);
        }

        return null;
    }

   public void deleteProduct(Long id) {
       Product existingProduct = findProductById(id);

        if (existingProduct != null){
            productRepository.deleteById(existingProduct.getId());
        }
    }
    public void validateDescriptionLength(String description){
        if (description.length() <= 5 || description.length() >= 300){
            throw new DescriptionLengthException("O tamanho da descrição precisa ser maior 5 e menor que 300.");
        }
    }
    public void validateNameLength(String name){
        if (name.length() > 50){
            throw new NameLengthException("O tamanho do nome precisa ser até 50.");
        }
    }
    private void validatePrice(BigDecimal value) {
        final String message = String.format("O valor %s é inválido.", value);

        if (value == null) {
            throw new InvalidValueException(message);
        }

        if (this.incorrectValue(value)) {
            throw new InvalidValueException(message);
        }
    }
    private boolean incorrectValue(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) <= 0;
    }
}
