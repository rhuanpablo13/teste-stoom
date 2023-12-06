package br.com.stoom.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.stoom.store.response.CategoryResponse;
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
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "category")
    private String category;

    @Column(name = "active")
    private Boolean active;

    
    public CategoryResponse toCategoryResponse() {
        return CategoryResponse.builder()
        .id(id)
        .category(category)
        .active(active)
        .build();
    }
}
