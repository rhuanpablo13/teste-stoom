package br.com.stoom.store.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    
    private Long id;
    private String sku;
    private CategoryResponse category;
    private BrandResponse brand;
    private BigDecimal price;
    private String name;
    private Boolean active;
}
