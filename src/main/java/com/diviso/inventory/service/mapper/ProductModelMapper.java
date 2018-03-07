package com.diviso.inventory.service.mapper;

import org.mapstruct.Mapper;

import com.diviso.inventory.domain.Product;
import com.diviso.inventory.model.ProductModel;

@Mapper(componentModel = "spring")
public interface ProductModelMapper extends ModelMapper<Product,ProductModel> {

}
