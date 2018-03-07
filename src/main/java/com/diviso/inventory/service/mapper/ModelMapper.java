package com.diviso.inventory.service.mapper;

import java.util.List;



public interface ModelMapper<E,M> {
	
	  E toEntity(M model);

	    M toModel(E entity);

	    List <E> toEntity(List<M> modelList);

	    List <M> toModel(List<E> entityList);
	    


}
