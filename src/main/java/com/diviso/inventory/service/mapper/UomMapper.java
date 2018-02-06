package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.UomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Uom and its DTO UomDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UomMapper extends EntityMapper<UomDTO, Uom> {


    @Mapping(target = "stockLines", ignore = true)
    Uom toEntity(UomDTO uomDTO);

    default Uom fromId(Long id) {
        if (id == null) {
            return null;
        }
        Uom uom = new Uom();
        uom.setId(id);
        return uom;
    }
}
