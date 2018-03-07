package com.diviso.inventory.service.mapper;

import org.mapstruct.Mapper;

import com.diviso.inventory.domain.Category;
import com.diviso.inventory.model.CategoryModel;
@Mapper(componentModel="spring")
public interface CategoryModelMapper extends ModelMapper<Category, CategoryModel> {

}
