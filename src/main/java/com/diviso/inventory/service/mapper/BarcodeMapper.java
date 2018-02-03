package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.BarcodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Barcode and its DTO BarcodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BarcodeMapper extends EntityMapper<BarcodeDTO, Barcode> {



    default Barcode fromId(Long id) {
        if (id == null) {
            return null;
        }
        Barcode barcode = new Barcode();
        barcode.setId(id);
        return barcode;
    }
}
