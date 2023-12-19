package br.com.loja.projetojavaglobo.model.entities;

import br.com.loja.projetojavaglobo.model.dto.ResponseProductDto;
import br.com.loja.projetojavaglobo.model.dto.ResponseUpdateProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O nome não pode estar em branco.")
    @Length(max = 100)
    private String name;
    @NotEmpty(message = "Campo descrição precisa ser preenchido.")
    @Length(min = 5, max = 150)
    private String description;
    @NotNull(message = "Campo preço precisa ser preenchido.")
    private BigDecimal price;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public ResponseProductDto toProductDto(){
        ResponseProductDto dto = new ResponseProductDto();
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setCreatedAt(this.createdAt);

        return dto;
    }
    public ResponseUpdateProductDto toUpdateProductDto(){
        ResponseUpdateProductDto dto = new ResponseUpdateProductDto();
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setCreatedAt(this.createdAt);
        dto.setUpdatedAt(LocalDateTime.now());

        return dto;
    }
}
