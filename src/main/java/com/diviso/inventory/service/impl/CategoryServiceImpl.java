package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.CategoryService;
import com.diviso.inventory.domain.Category;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.model.CategoryModel;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.repository.CategoryRepository;
import com.diviso.inventory.service.dto.CategoryDTO;
import com.diviso.inventory.service.mapper.CategoryMapper;
import com.diviso.inventory.service.mapper.CategoryModelMapper;
import com.diviso.inventory.service.mapper.ProductModelMapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    private final CategoryModelMapper categoryModelMapper;
    private final ProductModelMapper productModelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper,CategoryModelMapper categoryModelMapper,ProductModelMapper productModelMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryModelMapper=categoryModelMapper;
        this.productModelMapper=productModelMapper;
    }

    /**
     * Save a category.
     *
     * @param categoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll(pageable)
            .map(categoryMapper::toDto);
    }

    /**
     * Get one category by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        Category category = categoryRepository.findOne(id);
        return categoryMapper.toDto(category);
    }

    /**
     * Delete the category by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
    }

/*	@Override
	public List<CategoryModel> findAllCategories(Pageable pageable) {
		Page<Category> categories=categoryRepository.findAllCategories(pageable);
		List<CategoryModel> categoryModelList=new ArrayList<CategoryModel>();
		for(Category category:categories.getContent()) {
			CategoryModel categoryModel=categoryModelMapper.toModel(category);
			List<ProductModel> productModelList=new ArrayList<ProductModel>();
				for(Product product:category.getProducts()) {
					ProductModel productModel=productModelMapper.toModel(product);
					productModelList.add(productModel);
					categoryModel.setProducts(productModelList);
			}
				categoryModelList.add(categoryModel);
		}
		return categoryModelList;
	}*/
}
