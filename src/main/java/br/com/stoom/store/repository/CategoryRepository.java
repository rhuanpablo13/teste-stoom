package br.com.stoom.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.stoom.store.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * 
     * @param categroy
     * @return
     */
    @Query("select count(c) from Category c where c.category =:category")
    Integer countByCategoryName(@Param("category") String categroy);

    /**
     * 
     * @param category
     * @return
     */
    Optional<Category> findByCategory(@Param("category") String category);

    /**
     *
     * @param active
     * @return
     */
    List<Category> findAllByActive(@Param("active") Boolean active);
}
