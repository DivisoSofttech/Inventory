package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Barcode;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Barcode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

}
