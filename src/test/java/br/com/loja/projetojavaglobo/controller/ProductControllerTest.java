package br.com.loja.projetojavaglobo.controller;

import br.com.loja.projetojavaglobo.model.entities.Product;
import br.com.loja.projetojavaglobo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Mock
    ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @InjectMocks
    private ProductController productController;



    /*@Test
    public void testCreateProduct() throws Exception{
        RequestProductDto requestProductDto = new RequestProductDto("Caneta Bic", "Cor azul", BigDecimal.ONE);
        Product mockProduct = new Product();
        mockProduct.setId(1L);

        when(productService.createProduct(requestProductDto)).thenReturn(mockProduct);

        mockMvc.perform(post("/api/produtos/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(productService, times(1)).createProduct(requestProductDto);
    }*/
    @Test
    public void testFindProducts() throws Exception{

        when(productService.findAllProducts()).thenReturn(List.of(new Product(), new Product()));

        mockMvc.perform((RequestBuilder) get("/api/produtos"))
                .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$.length()").value(2));


        verify(productService, times(1)).findAllProducts();
    }
}
