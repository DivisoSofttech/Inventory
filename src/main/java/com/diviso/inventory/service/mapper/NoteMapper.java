package com.diviso.inventory.service.mapper;

import com.diviso.inventory.domain.*;
import com.diviso.inventory.service.dto.NoteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Note and its DTO NoteDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface NoteMapper extends EntityMapper<NoteDTO, Note> {

    @Mapping(source = "product.id", target = "productId")
    NoteDTO toDto(Note note);

    @Mapping(source = "productId", target = "product")
    Note toEntity(NoteDTO noteDTO);

    default Note fromId(Long id) {
        if (id == null) {
            return null;
        }
        Note note = new Note();
        note.setId(id);
        return note;
    }
}
