package io.github.arthur32p.libraryapi.controller.mappers;

import io.github.arthur32p.libraryapi.model.Usuario;
import io.github.arthur32p.libraryapi.model.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDto(Usuario usuario);
}
