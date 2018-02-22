package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Stock;
import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.service.dto.StockDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the Stock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("select distinct stock from Stock stock left join fetch stock.stockLines")
    List<Stock> findAllWithEagerRelationships();

    @Query("select stock from Stock stock left join fetch stock.stockLines where stock.id =:id")
    Stock findOneWithEagerRelationships(@Param("id") Long id);

	Stock findByReference(String reference);

	Stock findByDeliveryNoteRef(String deliveryNoteRef);

	Page<Stock> findByDateOfStockUpdated(LocalDate dateOfStockUpdated, Pageable pageable);

	Page<Stock> findByDateOfStockUpdatedBetween(LocalDate from, LocalDate to, Pageable pageable);

	Page<Stock> findByStatus_Name(String status, Pageable pageable);

	@Query("select distinct sl from Stock s join s.stockLines sl where s.id=:id")
	Set<StockLine> findStockLinesByStockId(@Param("id") Long id);

}
