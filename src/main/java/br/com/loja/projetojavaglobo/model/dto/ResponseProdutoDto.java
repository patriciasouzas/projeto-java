package br.com.loja.projetojavaglobo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseProdutoDto {
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
