package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.exceptions.DescriptionLengthException;
import br.com.loja.projetojavaglobo.exceptions.InvalidValueException;
import br.com.loja.projetojavaglobo.exceptions.NameLengthException;
import br.com.loja.projetojavaglobo.model.dto.RequestProductDto;
import br.com.loja.projetojavaglobo.model.dto.RequestUpdateProductDto;
import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Test
    void testFindAllProducts(){
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));

        List<Product> productList = productService.findAllProducts();

        assertNotNull(productList);
        assertFalse(productList.isEmpty());

        verify(productRepository, times(1)).findAll();
    }
    @Test
    void testFindProductById(){
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product foundProduct = productService.findProductById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());

        verify(productRepository, times(1)).findById(productId);
    }
    @Test
    void testCreateProduct(){
        RequestProductDto request = new RequestProductDto("Caneta Bic", "Caneta azul", BigDecimal.ONE);

        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Product product = productService.createProduct(request);

        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
        assertEquals(request.getDescription(), product.getDescription());
        assertEquals(request.getPrice(), product.getPrice());

        verify(productRepository, times(1)).save(any());
    }
    @Test
    void testUpdateProduct(){
        Long productId = 1L;
        RequestUpdateProductDto updatedProductDto = new RequestUpdateProductDto("Caneta Esfereográfica", null, null);
        Product existingProduct = new Product();
        existingProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Product updatedProduct = productService.updateProduct(productId, updatedProductDto);

        assertNotNull(updatedProductDto);
        assertEquals(updatedProductDto.getName(), updatedProduct.getName());
        assertNull(updatedProduct.getDescription());
        assertNull(updatedProduct.getPrice());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any());
    }
    @Test
    void testDeleteProduct(){
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(any());
    }
    @Test
    void testValidateDescriptionLength(){
        assertThrows(DescriptionLengthException.class,
                () -> productService.validateDescriptionLength("Teste"),
                "O tamanho da descrição precisa ser maior 5 e menor que 300.");

        assertThrows(DescriptionLengthException.class,
                () -> productService.validateDescriptionLength("Teste que seja maior que 300 para verificar se o teste da exceptions está funcionando certoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"),
                "O tamanho da descrição precisa ser maior 5 e menor que 300.");

        assertDoesNotThrow(() -> productService.validateDescriptionLength("Caneta cor azul"));
    }
    @Test
    void testValidateNameLength(){
        assertThrows(NameLengthException.class,
                () -> productService.validateNameLength("Carlos Alberto Joaquim Novaes de Oliveira Sebastiando"),
                "O tamanho do nome precisa até 50.");

        assertDoesNotThrow(() -> productService.validateDescriptionLength("Carlos Alberto"));
    }
    @Test
    void testValidatePrice(){
        assertThrows(InvalidValueException.class,
                () -> productService.validatePrice(null),
                "O valor null é inválido.");
        assertThrows(InvalidValueException.class,
                () -> productService.validatePrice(new BigDecimal(-50)),
                "O valor -50 é inválido.");

        assertDoesNotThrow(() -> productService.validatePrice(BigDecimal.TEN));
    }
}
