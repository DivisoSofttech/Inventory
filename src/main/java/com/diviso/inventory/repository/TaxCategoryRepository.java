package com.diviso.inventory.repository;

import com.diviso.inventory.domain.TaxCategory;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TaxCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxCategoryRepository extends JpaRepository<TaxCategory, Long> {

}
