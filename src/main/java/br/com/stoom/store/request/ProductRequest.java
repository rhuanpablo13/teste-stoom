package br.com.stoom.store.request;

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
public class ProductRequest {
    
    private String sku;
    private String category;
    private String brand;
    private String price;
    private String name;
    private Boolean active;
}
