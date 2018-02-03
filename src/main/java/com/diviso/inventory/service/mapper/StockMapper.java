package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.StockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Stock and its DTO StockDTO.
 */
@Mapper(componentModel = "spring", uses = {StatusMapper.class, StockLineMapper.class})
public interface StockMapper extends EntityMapper<StockDTO, Stock> {

    @Mapping(source = "status.id", target = "statusId")
    StockDTO toDto(Stock stock);

    @Mapping(source = "statusId", target = "status")
    Stock toEntity(StockDTO stockDTO);

    default Stock fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stock stock = new Stock();
        stock.setId(id);
        return stock;
    }
}
