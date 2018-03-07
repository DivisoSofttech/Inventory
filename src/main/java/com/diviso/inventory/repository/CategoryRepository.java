package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value="select distinct * from Category")
	Page<Category> findAllCategories(Pageable pageable);

}
