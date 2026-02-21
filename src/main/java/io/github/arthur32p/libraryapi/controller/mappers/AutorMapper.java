package io.github.arthur32p.libraryapi.controller.mappers;

import io.github.arthur32p.libraryapi.controller.dto.AutorDTO;
import io.github.arthur32p.libraryapi.controller.dto.AutorResponse;
import io.github.arthur32p.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

    AutorResponse toResponse(Autor autor);
}
