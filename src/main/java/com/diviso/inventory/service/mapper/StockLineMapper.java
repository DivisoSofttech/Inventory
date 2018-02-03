package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.StockLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockLine and its DTO StockLineDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StockLineMapper extends EntityMapper<StockLineDTO, StockLine> {

    @Mapping(source = "product.id", target = "productId")
    StockLineDTO toDto(StockLine stockLine);

    @Mapping(source = "productId", target = "product")
    StockLine toEntity(StockLineDTO stockLineDTO);

    default StockLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockLine stockLine = new StockLine();
        stockLine.setId(id);
        return stockLine;
    }
}
