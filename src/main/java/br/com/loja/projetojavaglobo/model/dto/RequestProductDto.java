package br.com.loja.projetojavaglobo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestProductDto {
    private String name;
    private String description;
    private BigDecimal price;
}