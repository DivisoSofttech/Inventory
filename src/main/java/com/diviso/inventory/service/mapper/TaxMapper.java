package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.TaxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tax and its DTO TaxDTO.
 */
@Mapper(componentModel = "spring", uses = {TaxCategoryMapper.class})
public interface TaxMapper extends EntityMapper<TaxDTO, Tax> {

    @Mapping(source = "taxCategory.id", target = "taxCategoryId")
    TaxDTO toDto(Tax tax);

    @Mapping(source = "taxCategoryId", target = "taxCategory")
    Tax toEntity(TaxDTO taxDTO);

    default Tax fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tax tax = new Tax();
        tax.setId(id);
        return tax;
    }
}
