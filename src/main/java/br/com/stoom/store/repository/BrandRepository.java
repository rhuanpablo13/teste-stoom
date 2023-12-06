package br.com.stoom.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    /**
     * 
     * @param brand
     * @return
     */
    @Query("select count(b) from Brand b where b.brand =:brand")
    Integer countByBrandName(@Param("brand") String brand);

    /**
     * 
     * @param brand
     * @return
     */
    Optional<Brand> findByBrand(@Param("brand") String brand);

    /**
     * 
     * @param active
     * @return
     */
    List<Brand> findAllByActive(@Param("active") Boolean active);
}
