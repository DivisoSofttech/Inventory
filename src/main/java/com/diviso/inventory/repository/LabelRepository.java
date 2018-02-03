package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Label;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Label entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

}
