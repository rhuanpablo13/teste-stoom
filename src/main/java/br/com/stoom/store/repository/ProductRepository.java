package br.com.stoom.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select count(p) from Product p where p.sku =:sku")
    Integer countBySku(@Param("sku") String sku);

    List<Product> findAllByBrand(@Param("brand") Brand brand);

    List<Product> findAllByCategory(@Param("category") Category category);

    List<Product> findAllByActive(@Param("active") Boolean active);

    Optional<Product> findBySku(@Param("sku") String sku);
}