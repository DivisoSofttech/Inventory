package com.diviso.inventory.repository;

import com.diviso.inventory.domain.Note;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.service.dto.ProductDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select distinct product from Product product left join fetch product.labels")
    List<Product> findAllWithEagerRelationships();

    @Query("select product from Product product left join fetch product.labels where product.id =:id")
    Product findOneWithEagerRelationships(@Param("id") Long id);

	Page<Product> findByNameIgnoreCaseAndVisibleTrue(String name, Pageable pageable);

	Page<Product> findByVisibleTrue(Pageable pageable);

	Page<Product> findByVisibleFalse(Pageable pageable);

	Page<Product> findByNameIgnoreCaseContainingAndVisibleTrue(Pageable pageable);

	Product findByReferenceIgnoreCaseAndVisibleTrue(String reference);

	Product findBySkuIgnoreCaseAndVisibleTrue(String sku);

	Product findBySearchkeyIgnoreCaseAndVisibleTrue(String searchkey);

	Product findByMpnIgnoreCaseAndVisibleTrue(String mpn);

	Product findByBarcode_CodeAndVisibleTrue(String barcode);

	Page<Product> findByCategory_NameIgnoreCaseAndVisibleTrue(String name, Pageable pageable);

	Page<Product> findByDateOfMfdAndVisibleTrue(LocalDate dateOfMfd, Pageable pageable);

	Page<Product> findByDateOfExpiryAndVisibleTrue(LocalDate dateOfExpiry, Pageable pageable);

	Page<Product> findByDateOfExpiryBetweenAndVisibleTrue(LocalDate from, LocalDate to, Pageable pageable);

	Page<Product> findByStatus_NameIgnoreCaseAndVisibleTrue(String status, Pageable pageable);

	@Query("select distinct n from Product p join  p.notes n where p.id=:id")
	Page<Note> findNotesByProductId(@Param("id")Long id,Pageable pageable);

	/*@Query("select distinct p,b,s,c,tc from Product p join p.barcode b join p.status s join p.category c join p.taxCategory tc where p.id=:id ")
	ProductModel findById(@Param("id") Long id);
*/
}
