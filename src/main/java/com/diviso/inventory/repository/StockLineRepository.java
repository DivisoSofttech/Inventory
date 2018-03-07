package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Product;
import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.service.dto.StockLineDTO;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StockLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockLineRepository extends JpaRepository<StockLine, Long> {

	Page<StockLine> findByProduct_NameIgnoreCaseAndProduct_VisibleTrue(String name, Pageable pageable);

	Page<StockLine> findByProduct_NameIgnoreCaseContainingAndProduct_VisibleTrue(Pageable pageable);

	Page<StockLine> findByProduct_VisibleFalse(Pageable pageable);

	Page<StockLine> findByProduct_VisibleTrue(Pageable pageable);

	StockLine findByProduct_ReferenceIgnoreCaseAndProduct_VisibleTrue(String reference);

	StockLine findByProduct_SkuIgnoreCaseAndProduct_VisibleTrue(String sku);

	StockLine findByProduct_SearchkeyIgnoreCaseAndProduct_VisibleTrue(String searchkey);

	StockLine findByProduct_MpnIgnoreCaseAndProduct_VisibleTrue(String mpn);

	StockLine findByProduct_Barcode_CodeAndProduct_VisibleTrue(String barcode);

	Page<StockLine> findByProduct_DateOfMfdAndProduct_VisibleTrue(LocalDate dateOfMfd, Pageable pageable);

	Page<StockLine> findByProduct_DateOfExpiryAndProduct_VisibleTrue(LocalDate dateOfExpiry, Pageable pageable);

	Page<StockLine> findByProduct_DateOfExpiryBetweenAndProduct_VisibleTrue(LocalDate from, LocalDate to, Pageable pageable);

	Page<StockLine> findByProduct_Status_NameIgnoreCaseAndProduct_VisibleTrue(String status, Pageable pageable);

	Page<StockLine> findByProduct_Category_NameIgnoreCaseAndProduct_VisibleTrue(String name, Pageable pageable);

	StockLine findByReference(String reference);

	Page<StockLine> findByInfrastructureId(Long infrastructureId, Pageable pageable);

	Page<StockLine> findByLocationId(Long locationId, Pageable pageable);

	Page<StockLine> findBySupplierRef(Long supplierRef, Pageable pageable);

	Page<StockLine> findByProduct_Category_IdAndProduct_VisibleTrue(Long id, Pageable pageable);

}
