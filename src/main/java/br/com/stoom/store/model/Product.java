package br.com.stoom.store.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.com.stoom.store.response.ProductResponse;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku", unique = true)
    private String sku;

    @OneToOne()
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne()
    @JoinColumn(name = "brand")
    private Brand brand;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;


    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
        .id(id)
        .name(name)
        .price(price)
        .sku(sku)
        .active(active)
        .category(category.toCategoryResponse())
        .brand(brand.toBrandResponse())
        .build();
    }
}