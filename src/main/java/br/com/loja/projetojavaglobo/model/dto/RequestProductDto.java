package br.com.loja.projetojavaglobo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestProductDto {
    public RequestProductDto(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    private String name;
    private String description;
    private BigDecimal price;
}
