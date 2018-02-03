package com.diviso.inventory.repository;

import com.diviso.inventory.domain.StockLine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StockLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockLineRepository extends JpaRepository<StockLine, Long> {

}
