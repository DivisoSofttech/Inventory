package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {BarcodeMapper.class, UomMapper.class, CategoryMapper.class, LabelMapper.class, StatusMapper.class, TaxCategoryMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "barcode.id", target = "barcodeId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "taxCategory.id", target = "taxCategoryId")
    ProductDTO toDto(Product product);

    @Mapping(source = "barcodeId", target = "barcode")
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "stockLines", ignore = true)
    @Mapping(target = "taxes", ignore = true)
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "taxCategoryId", target = "taxCategory")
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
