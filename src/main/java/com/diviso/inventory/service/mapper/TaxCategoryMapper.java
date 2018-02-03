package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.TaxCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TaxCategory and its DTO TaxCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaxCategoryMapper extends EntityMapper<TaxCategoryDTO, TaxCategory> {


    @Mapping(target = "taxes", ignore = true)
    @Mapping(target = "products", ignore = true)
    TaxCategory toEntity(TaxCategoryDTO taxCategoryDTO);

    default TaxCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaxCategory taxCategory = new TaxCategory();
        taxCategory.setId(id);
        return taxCategory;
    }
}
