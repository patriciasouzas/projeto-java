package br.com.loja.projetojavaglobo.model.entities;

import br.com.loja.projetojavaglobo.model.dto.ResponseProdutoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt = LocalDateTime.now();

    public ResponseProdutoDto toProdutoDto(){
        ResponseProdutoDto dto = new ResponseProdutoDto();
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setCreatedAt(this.createdAt);

        return dto;
    }
}
