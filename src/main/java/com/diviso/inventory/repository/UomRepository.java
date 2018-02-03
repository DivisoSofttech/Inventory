package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Uom;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Uom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UomRepository extends JpaRepository<Uom, Long> {

}
