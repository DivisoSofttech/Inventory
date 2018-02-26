package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Tax;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tax entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

	
	Page<Tax> findByTaxCategory_Id(Long id,Pageable pageable);

}
