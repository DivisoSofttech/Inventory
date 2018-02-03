package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {BarcodeMapper.class, UomMapper.class, LabelMapper.class, StatusMapper.class, TaxCategoryMapper.class, TaxMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "barcode.id", target = "barcodeId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "taxCategory.id", target = "taxCategoryId")
    @Mapping(source = "tax.id", target = "taxId")
    ProductDTO toDto(Product product);

    @Mapping(source = "barcodeId", target = "barcode")
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "stockLines", ignore = true)
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "taxCategoryId", target = "taxCategory")
    @Mapping(source = "taxId", target = "tax")
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
