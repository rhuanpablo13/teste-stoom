package br.com.stoom.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.stoom.store.response.BrandResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "brand")
    private String brand;

    @Column(name = "active")
    private Boolean active;

    
    public BrandResponse toBrandResponse() {
        return BrandResponse.builder()
        .id(id)
        .brand(brand)
        .active(active)
        .build();
    }
}
