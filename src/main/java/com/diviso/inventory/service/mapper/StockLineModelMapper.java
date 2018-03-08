package com.diviso.inventory.service.mapper;

import org.mapstruct.Mapper;

import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.model.StockLineModel;

@Mapper(componentModel = "spring")
public interface StockLineModelMapper extends ModelMapper<StockLine, StockLineModel> {

}
